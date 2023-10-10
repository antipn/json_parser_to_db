
package com.json.parser.db.antipn;

import com.json.parser.db.antipn.converter.JsonToObjects;
import com.json.parser.db.antipn.models.OutputSearchJsonObject;
import com.json.parser.db.antipn.models.searching.Search;
import com.json.parser.db.antipn.models.sqlObjects.ProductSQL;
import com.json.parser.db.antipn.reader.JsonReader;

import com.json.parser.db.antipn.repositories.CustomerRepository;
import com.json.parser.db.antipn.worker.SearchWorker;
import com.json.parser.db.antipn.writer.JsonWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class JsonParserDBApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(JsonParserDBApplication.class, args);

        List<HashMap<String, String>> data = JsonReader.getData("input.json");

        List<? super Search> converter = JsonToObjects.converter(data);

        List<OutputSearchJsonObject> list = context.getBean(SearchWorker.class).preparingOutputData(converter);

        String outputJson = context.getBean(JsonWriter.class).generateResultJson(list);

        System.out.println(outputJson);

        //test

        CustomerRepository customerRepo = context.getBean(CustomerRepository.class);
        customerRepo.deleteFromWDays();
        //customerRepo.fillingWDays(LocalDate.of(2023, 10, 01), LocalDate.of(2023, 10, 9));
        customerRepo.fillingWDays("2023-10-01","2023-10-09");
        Optional<List<ProductSQL>> statisticByUserID = customerRepo.getStatisticByUserID(1L);
        statisticByUserID.get().forEach(item -> System.out.println(item.getProductName() + " " + item.getExpenses()));

    }
}
