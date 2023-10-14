package com.json.parser.db.antipn.converter;

import com.json.parser.db.antipn.exception.JsonException;
import com.json.parser.db.antipn.models.searching.*;

import java.util.*;

public class JsonToObjects {

    public static List<Search> converter(List<HashMap<String, String>> inputData) throws Exception {
        List<Search> outputData = new ArrayList<>();

        for (HashMap<String, String> node : inputData) {

            if (node.containsKey("lastName")) {
                SearchByLastName searchByLastName = new SearchByLastName(node.get("lastName"));
                outputData.add(searchByLastName);
            } else if (node.containsKey("productName") && node.containsKey("minTimes")) {
                SearchByProductNameAndQuantity searchByProductNameAndQuantity = new SearchByProductNameAndQuantity();

                try {
                    int minTimes = Integer.parseInt(node.get("minTimes"));
                    searchByProductNameAndQuantity.setProductName(node.get("productName"));
                    searchByProductNameAndQuantity.setMinTimes(minTimes);

//                    for (String key : node.keySet()) {
//                        switch (key) {
//                            case "productName":
//
//                                break;
//                            case "minTimes":
//                                searchByProductNameAndQuantity.setMinTimes(minTimes);
//                                break;
//                        }
//                    }
                    outputData.add(searchByProductNameAndQuantity);
                } catch (NumberFormatException e) {
                    throw new JsonException("поле minTimes не содержит число");
                }


            } else if (node.containsKey("minExpenses") && node.containsKey("maxExpenses")) {
                SearchByMinAndMaxProductPrice searchByMinAndMaxProductPrice = new SearchByMinAndMaxProductPrice();

                try {
                    int minExpenses = Integer.parseInt(node.get("minExpenses"));
                    int maxExpenses = Integer.parseInt(node.get("maxExpenses"));
                    searchByMinAndMaxProductPrice.setMinExpenses(minExpenses);
                    searchByMinAndMaxProductPrice.setMaxExpenses(maxExpenses);

//                    for (String key : node.keySet()) {
//                        switch (key) {
//                            case "minExpenses":
//                    searchByMinAndMaxProductPrice.setMinExpenses(Integer.parseInt(node.get("minExpenses")));
//                                break;
//                            case "maxExpenses":
//                    searchByMinAndMaxProductPrice.setMaxExpenses(Integer.parseInt(node.get("maxExpenses")));
//                              break;
//                        }
//                    }
                    outputData.add(searchByMinAndMaxProductPrice);
                } catch (NumberFormatException e) {
                    throw new JsonException("поле minExpenses или maxExpenses не содержит число");
                }
            } else if (node.containsKey("badCustomers")) {

                try {
                    Integer badCustomers = Integer.parseInt(node.get("badCustomers"));
                    SearchBadCustomers searchBadCustomers = new SearchBadCustomers(badCustomers);
                    outputData.add(searchBadCustomers);
                } catch (NumberFormatException e) {
                    throw new JsonException("поле badCustomers не содержит число");
                }

            } else {
                throw new JsonException("Проблема с наименованием полей для критерия, возможно опечатка, проверьте названия полей ввода");
            }

        }
        return outputData;
    }
}
