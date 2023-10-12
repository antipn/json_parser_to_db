
package com.json.parser.db.antipn;

import com.json.parser.db.antipn.converter.JsonToObjects;
import com.json.parser.db.antipn.dto.StatisticsDto;
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

        if (args.length < 3) {
            throw new Exception("Количество аргументов меньше 3, должны быть 3");
        }

        switch (args[0]) {

            case "search":
                System.out.println("Критерий - search");
                List<HashMap<String, String>> data = JsonReader.getSearchingData(args[1]);
                List<Search> converter = JsonToObjects.converter(data);
                List<OutputSearchJsonObject> list = context.getBean(SearchWorker.class).preparingOutputData(converter);
                String outputJsonSearch = context.getBean(JsonSearchWriter.class).generateResultJson(list, args[2]);
                System.out.println("Выводим наш search JSON");
                System.out.println(outputJsonSearch);
                break;

            case "stat":
                System.out.println("Критерий - stat");
                StatisticsDto statisticsData = JsonReader.getStatisticsData(args[1]);
                StatisticsWorker stat = context.getBean(StatisticsWorker.class);
                OutputStatisticsJsonObject outputStatisticsJsonObject = stat.preparingOutputData(statisticsData.getStartDate(), statisticsData.getEndDate());
                String outputJsonStat = context.getBean(JsonStatisticsWriter.class).generateResultJson(outputStatisticsJsonObject, args[2]);
                System.out.println(outputJsonStat);
                break;

            default:
                throw new Exception("Неправильное имя критерия, должны быть 'search' или 'stat', получили " + args[0]);

        }
    }
}
