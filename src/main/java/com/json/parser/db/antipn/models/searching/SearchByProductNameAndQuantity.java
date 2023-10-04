package com.json.parser.db.antipn.models.searching;

public class SearchByProductNameAndQuantity implements Search {

    SearchType searchType = SearchType.SEARCH_PRODUCT_BY_NAME_AND_MIN_QUANTITY;

    String productName;
    Integer minTimes;

    @Override
    public SearchType getSearchType() {
        return searchType;
    }
}
