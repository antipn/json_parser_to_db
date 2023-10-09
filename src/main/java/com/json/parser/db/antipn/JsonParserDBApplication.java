
package com.json.parser.db.antipn;

import com.json.parser.db.antipn.converter.JsonToObjects;
import com.json.parser.db.antipn.models.OutputSearchJsonObject;
import com.json.parser.db.antipn.models.searching.Search;
import com.json.parser.db.antipn.reader.JsonReader;

import com.json.parser.db.antipn.worker.SearchWorker;
import com.json.parser.db.antipn.writer.JsonWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.List;


@SpringBootApplication
public class JsonParserDBApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(JsonParserDBApplication.class, args);

        List<HashMap<String, String>> data = JsonReader.getData("input.json");

        List<? super Search> converter = JsonToObjects.converter(data);

        List<OutputSearchJsonObject> list = context.getBean(SearchWorker.class).preparingOutputData(converter);

        String outputJson = context.getBean(JsonWriter.class).generateResultJson(list);

        System.out.println(outputJson);

    }
}
