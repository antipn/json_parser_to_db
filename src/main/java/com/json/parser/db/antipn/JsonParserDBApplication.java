
package com.json.parser.db.antipn;

import com.json.parser.db.antipn.reader.JsonReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class JsonParserDBApplication {

    public static void main(String[] args) throws Exception {
        //ConfigurableApplicationContext context = SpringApplication.run(JsonParserDBApplication.class, args);

        JsonReader.getData("input.json");
    }

}
