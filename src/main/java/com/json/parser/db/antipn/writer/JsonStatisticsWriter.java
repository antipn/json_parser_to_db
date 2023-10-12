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

    public String generateResultJson(OutputStatisticsJsonObject inputData, String fileName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(inputData);

        try (FileOutputStream fos = new FileOutputStream(fileName);
             OutputStreamWriter outputFile = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
            outputFile.write(json);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("There is problem with file " + fileName);
        }

        return json;

    }
}
