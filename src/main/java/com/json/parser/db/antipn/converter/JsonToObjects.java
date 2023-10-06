package com.json.parser.db.antipn.converter;

import com.json.parser.db.antipn.models.searching.Search;

import java.util.HashMap;
import java.util.List;

public class JsonToObjects {

    public static List<Search> converter(List<HashMap<String, String>> inputData) {
        System.out.println("_____________________________________________________");
        List<Search> outputData = null;
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");

//        inputData.stream()
//                        .map(inMap -> {
//                            System.out.println("+++++++++++++++++++++++++++++++++++++++++");
//                            System.out.println(inMap.entrySet());
//
//
//                            return null;
//                        }).collect(Collectors.toList());
//
//        System.out.println("+++++++++++++++++++++++++++++++++++++++++");

        for (HashMap<String, String> node : inputData) {

            for (HashMap.Entry<String, String> inMap : node.entrySet()) {
                System.out.println(inMap.getKey() + " " + inMap.getValue());

            }
            System.out.println("_____________________________");
        }
        return outputData;
    }
}
