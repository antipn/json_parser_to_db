
package com.json.parser.db.antipn;

import com.json.parser.db.antipn.converter.JsonToObjects;
import com.json.parser.db.antipn.reader.JsonReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class JsonParserDBApplication {

    public static void main(String[] args) throws Exception {
        //ConfigurableApplicationContext context = SpringApplication.run(JsonParserDBApplication.class, args);

        List<HashMap<String, String>> data = JsonReader.getData("input.json");
        JsonToObjects.converter(data);

    }

}
