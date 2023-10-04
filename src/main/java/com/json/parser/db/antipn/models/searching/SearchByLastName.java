package com.json.parser.db.antipn.models.searching;

public class SearchByLastName implements Search {

    SearchType searchType = SearchType.SEARCH_CUSTOMER_BY_LASTNAME;

    public String lastName;

    @Override
    public SearchType getSearchType() {
        return searchType;
    }
}
