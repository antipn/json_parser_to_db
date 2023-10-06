package com.json.parser.db.antipn.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.URL;
import java.util.*;


public class JsonReader {

    static public List<HashMap<String, String>> getData(String fileName) throws Exception {

        InputStream inputData = null;

        ObjectMapper mapper = new ObjectMapper();

        try {
            URL file = JsonReader.class.getResource("/" + fileName);
            if (file == null) throw new Exception("Файл не существует");
            inputData = JsonReader.class.getClassLoader().getResourceAsStream(fileName);

            inputData.mark(1);
            int read = inputData.read(new byte[1]);
            inputData.reset();
            if (read == -1) {
                throw new Exception("Нет данных в файле");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("There is problem with file " + fileName);
            throw new Exception("Ошибка");
        }


        JsonNode rootNode = mapper.readTree(inputData);

        JsonNode locatedNode = rootNode.path("criterias");

        //System.out.println("Выводим наш массив критериев поиска" + locatedNode.toString());
        //System.out.println("Выводим поля и значения по очереди");
        //Iterator<Map.Entry<String, JsonNode>> fields = locatedNode.fields();


        int i = 0;
        List<HashMap<String, String>> data = new ArrayList<>();

        for (JsonNode node : locatedNode) {
            //System.out.println("Обнаружен критерий поиска " + node.isContainerNode());
            HashMap<String, String> nodeData = new HashMap<>();

            Iterator<Map.Entry<String, JsonNode>> nodeFields = node.fields();


            while (nodeFields.hasNext()) {

                Map.Entry<String, JsonNode> next = nodeFields.next();
                //System.out.println("Поле " + i + " кол-во элементов в поле " + node.size());
                System.out.println("ключ: " + next.getKey() + " значение: " + next.getValue().asText());
                nodeData.put(next.getKey(),next.getValue().asText());
            }
            data.add(nodeData);
            i++;
        }
        inputData.close();

        return data;
    }



}
