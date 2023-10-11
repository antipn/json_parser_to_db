package com.json.parser.db.antipn.converter;

import com.json.parser.db.antipn.models.searching.*;

import java.util.*;

public class JsonToObjects {

    public static List<Search> converter(List<HashMap<String, String>> inputData) throws Exception {
        List<Search> outputData = new ArrayList<>();

        for (HashMap<String, String> node : inputData) {

            if (node.containsKey("lastName")) {
                SearchByLastName searchByLastName = new SearchByLastName(node.get("lastName"));
                outputData.add(searchByLastName);
            }

            if (node.containsKey("productName") && node.containsKey("minTimes")) {
                SearchByProductNameAndQuantity searchByProductNameAndQuantity = new SearchByProductNameAndQuantity();
                for (String key : node.keySet()) {
                    switch (key) {
                        case "productName":
                            searchByProductNameAndQuantity.setProductName(node.get(key));
                            break;
                        case "minTimes":
                            searchByProductNameAndQuantity.setMinTimes(Integer.parseInt(node.get(key)));
                            break;
                    }
                }
                outputData.add(searchByProductNameAndQuantity);
            }

            if (node.containsKey("minExpenses") && node.containsKey("maxExpenses")) {
                SearchByMinAndMaxProductPrice searchByMinAndMaxProductPrice = new SearchByMinAndMaxProductPrice();
                for (String key : node.keySet()) {
                    switch (key) {
                        case "minExpenses":
                            searchByMinAndMaxProductPrice.setMinExpenses(Integer.parseInt(node.get(key)));
                            break;
                        case "maxExpenses":
                            searchByMinAndMaxProductPrice.setMaxExpenses(Integer.parseInt(node.get(key)));
                            break;
                    }
                }
                outputData.add(searchByMinAndMaxProductPrice);
            }

            if (node.containsKey("badCustomers")) {
                SearchBadCustomers searchBadCustomers = new SearchBadCustomers(Integer.parseInt(node.get("badCustomers")));
                outputData.add(searchBadCustomers);
            }

        }
        return outputData;
    }
}
