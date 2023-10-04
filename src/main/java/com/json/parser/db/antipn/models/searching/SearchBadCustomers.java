package com.json.parser.db.antipn.models.searching;

public class SearchBadCustomers implements Search {

    SearchType searchType = SearchType.SEARCH_BAD_CUSTOMER;

    Integer badCustomers;

    @Override
    public SearchType getSearchType() {
        return searchType;
    }
}
