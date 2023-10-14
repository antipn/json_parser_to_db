package com.json.parser.db.antipn.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.json.parser.db.antipn.dto.StatisticsDto;
import com.json.parser.db.antipn.exception.JsonException;

import java.io.*;
import java.util.*;


public class JsonReader {

    public static List<HashMap<String, String>> getSearchingData(String fileName) throws Exception {

        InputStream inputData = null;

        ObjectMapper mapper = new ObjectMapper();

        try {
            File file = new File(fileName);
            if (!file.exists()) throw new JsonException("Файл не существует");
            inputData = new BufferedInputStream(new FileInputStream(file));
            inputData.mark(1);
            int read = inputData.read(new byte[1]);
            inputData.reset();
            if (read == -1) {
                throw new JsonException("Нет данных в файле");
            }

            JsonNode rootNode = mapper.readTree(inputData);

            JsonNode locatedNode = rootNode.path("criterias");

            if (locatedNode.size() == 0) {
                throw new JsonException("Не удалось вычитать дерево критериев.");
            }

            List<HashMap<String, String>> data = new ArrayList<>();

            for (JsonNode node : locatedNode) {
                HashMap<String, String> nodeData = new HashMap<>();

                Iterator<Map.Entry<String, JsonNode>> nodeFields = node.fields();

                while (nodeFields.hasNext()) {
                    Map.Entry<String, JsonNode> next = nodeFields.next();
                    nodeData.put(next.getKey(), next.getValue().asText());
                }
                data.add(nodeData);
            }
            inputData.close();

            return data;

        } catch (JsonException e) {
            throw new JsonException(e.getMessage() + " Проблема с файлом " + fileName);
        }
    }

    public static StatisticsDto getStatisticsData(String fileName) throws Exception {

        StatisticsDto statisticsDto;
        ObjectMapper mapper = new ObjectMapper();

        try {

            File file = new File(fileName);
            if (!file.exists()) throw new JsonException("Файл не существует");

            statisticsDto = mapper.readValue(file, StatisticsDto.class);

        } catch (UnrecognizedPropertyException e) {
            throw new JsonException("Невозможно прочитать данные из файла для stat, должны быть два поля startDate и endDate с датами");
        } catch (JsonException e) {
            throw new JsonException(e.getMessage() + " Проблема с файлом " + fileName);
        }

        return statisticsDto;
    }
}
