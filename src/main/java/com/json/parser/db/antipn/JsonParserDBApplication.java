
package com.json.parser.db.antipn;

import com.json.parser.db.antipn.converter.JsonToObjects;
import com.json.parser.db.antipn.dto.StatisticsDto;
import com.json.parser.db.antipn.models.searching.OutputSearchJsonObject;
import com.json.parser.db.antipn.models.searching.Search;
import com.json.parser.db.antipn.models.statistics.OutputStatisticsJsonObject;
import com.json.parser.db.antipn.reader.JsonReader;

import com.json.parser.db.antipn.worker.SearchWorker;
import com.json.parser.db.antipn.worker.StatisticsWorker;
import com.json.parser.db.antipn.writer.JsonSeacrchWriter;
import com.json.parser.db.antipn.writer.JsonStatisticsWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.List;


@SpringBootApplication
public class JsonParserDBApplication {

    public static void main(String[] args) throws Exception {

        System.out.println("Аргументы приложения");
        for (String arg : args) {
            System.out.println(arg);
        }

        ConfigurableApplicationContext context = SpringApplication.run(JsonParserDBApplication.class, args);

        List<HashMap<String, String>> data = JsonReader.getSearchingData("input.json");

        List<Search> converter = JsonToObjects.converter(data);

        List<OutputSearchJsonObject> list = context.getBean(SearchWorker.class).preparingOutputData(converter);

        String outputJsonSearch = context.getBean(JsonSeacrchWriter.class).generateResultJson(list);

        System.out.println("Выводим наш search JSON");
        System.out.println(outputJsonSearch);


//        CustomerRepository customerRepo = context.getBean(CustomerRepository.class);
//        customerRepo.deleteAllRecordsFromWDaysTable();
//        //customerRepo.fillingWDays(LocalDate.of(2023, 10, 01), LocalDate.of(2023, 10, 9));
//        customerRepo.fillingWDaysTable("2023-10-01","2023-10-09");
//        Optional<List<ProductSQL>> statisticByUserID = customerRepo.getStatisticByCystomerId(1L);
//        statisticByUserID.get().forEach(item -> System.out.println(item.getProductName() + " " + item.getExpenses()));


        System.out.println("Statistics");

        StatisticsDto statisticsData = JsonReader.getStatisticsData(args[1]);

        StatisticsWorker stat = context.getBean(StatisticsWorker.class);
        OutputStatisticsJsonObject outputStatisticsJsonObject = stat.preparingOutputData(statisticsData.getStartDate(), statisticsData.getEndDate());
        String outputJsonStat = context.getBean(JsonStatisticsWriter.class).generateResultJson(outputStatisticsJsonObject);
        System.out.println(outputJsonStat);

    }
}
