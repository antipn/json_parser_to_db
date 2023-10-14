package com.json.parser.db.antipn.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Component
public class CommonWriter {

    public static void writeDataToFile(String json, String fileName) {

        try (FileOutputStream fos = new FileOutputStream(fileName);
             OutputStreamWriter outputFile = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
            outputFile.write(json);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("There is problem with file " + fileName);
        }

    }
}
