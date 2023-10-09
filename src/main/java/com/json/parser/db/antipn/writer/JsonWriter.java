package com.json.parser.db.antipn.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.json.parser.db.antipn.models.OutputSearchJsonObject;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JsonWriter {

    public String generateResultJson(List<OutputSearchJsonObject> inputData) throws IOException {


        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        json.put("type", "search");

        json.putPOJO("results", inputData);

        String string = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

        try (FileOutputStream fos = new FileOutputStream("c:/root/output.json");
             OutputStreamWriter outputFile = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
            outputFile.write(String.valueOf(string));
        }

        return string;
    }
}
