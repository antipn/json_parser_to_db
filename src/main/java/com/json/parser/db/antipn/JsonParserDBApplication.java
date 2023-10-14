
package com.json.parser.db.antipn;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.json.parser.db.antipn.converter.JsonToObjects;
import com.json.parser.db.antipn.dto.StatisticsDto;
import com.json.parser.db.antipn.exception.JsonException;
import com.json.parser.db.antipn.models.searching.OutputSearchJsonObject;
import com.json.parser.db.antipn.models.searching.Search;
import com.json.parser.db.antipn.models.statistics.OutputStatisticsJsonObject;
import com.json.parser.db.antipn.reader.JsonReader;

import com.json.parser.db.antipn.worker.SearchWorker;
import com.json.parser.db.antipn.worker.StatisticsWorker;
import com.json.parser.db.antipn.writer.JsonSearchWriter;
import com.json.parser.db.antipn.writer.JsonStatisticsWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;


@SpringBootApplication
public class JsonParserDBApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(JsonParserDBApplication.class, args);

        //Пример запуска  java -jar program.jar search input.json output.json

        //0 аргумент - search or stat
        //1 аргумент имя входного файла input.json
        //2 аргумент имя выходного файла output.json

        try {

            if (args.length < 3) {
                throw new Exception("Количество аргументов меньше 3, должны быть 3, получили " + args.length);
            }

            switch (args[0]) {
                case "search" -> {
                    System.out.println("Критерий - search");
                    List<HashMap<String, String>> data = JsonReader.getSearchingData(args[1]);
                    List<Search> converter = JsonToObjects.converter(data);
                    List<OutputSearchJsonObject> list = context.getBean(SearchWorker.class).preparingOutputData(converter);
                    String outputJsonSearch = context.getBean(JsonSearchWriter.class).generateResultJson(list, args[2]);
                    System.out.println("Выводим наш search JSON");
                    System.out.println(outputJsonSearch);
                }
                case "stat" -> {
                    System.out.println("Критерий - stat");
                    StatisticsDto statisticsData = JsonReader.getStatisticsData(args[1]);
                    StatisticsWorker stat = context.getBean(StatisticsWorker.class);
                    OutputStatisticsJsonObject outputStatisticsJsonObject = stat.preparingOutputData(statisticsData.getStartDate(), statisticsData.getEndDate());
                    String outputJsonStat = context.getBean(JsonStatisticsWriter.class).generateResultJson(outputStatisticsJsonObject, args[2]);
                    System.out.println("Выводим наш stat JSON");
                    System.out.println(outputJsonStat);
                }
                default ->
                        throw new JsonException("Неправильное имя критерия, должны быть 'search' или 'stat', получили " + args[0]);
            }
        } catch (JsonException e) {

            try {
                ObjectMapper mapper = new ObjectMapper();
                FileOutputStream fos = new FileOutputStream("error.json");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

                ObjectNode json = mapper.createObjectNode();
                json.put("type", "error");
                json.put("message", e.getMessage());

                String outputJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
                outputStreamWriter.write(outputJson);
                outputStreamWriter.close();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }


}
