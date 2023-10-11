package com.json.parser.db.antipn.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.parser.db.antipn.models.statistics.OutputStatisticsJsonObject;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Component
public class JsonStatisticsWriter {

    public String generateResultJson(OutputStatisticsJsonObject inputData) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(inputData);

        try (FileOutputStream fos = new FileOutputStream("c:/root/outputStat.json");
             OutputStreamWriter outputFile = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
            outputFile.write(String.valueOf(json));
        }

        return json;

    }
}
