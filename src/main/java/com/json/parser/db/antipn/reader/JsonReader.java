package com.json.parser.db.antipn.reader;

import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class JsonReader {

    public String filePath;

    public JsonReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getLines() {
        Path pathFile = Paths.get(filePath);
        boolean exists = Files.exists(pathFile);
        if (exists) {
            try {
                return Files.readAllLines(pathFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("There is problem with file " + pathFile.getFileName());
            }
        }
        return null;
    }

}
