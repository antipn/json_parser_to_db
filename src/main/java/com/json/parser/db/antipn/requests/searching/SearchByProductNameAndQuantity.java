package com.json.parser.db.antipn.requests.searching;

import com.json.parser.db.antipn.requests.Search;

public class SearchByProductNameAndQuantity implements Search {

    String productName;
    Integer minTimes;

    @Override
    public String search() {
        return null;
    }
}
