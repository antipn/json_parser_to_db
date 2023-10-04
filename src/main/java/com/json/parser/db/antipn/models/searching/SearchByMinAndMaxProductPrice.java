package com.json.parser.db.antipn.models.searching;

public class SearchByMinAndMaxProductPrice implements Search {

    SearchType searchType = SearchType.SEARCH_MIN_EXPENSES_AND_MAX_EXPENSES;

    Integer minExpenses;
    Integer maxExpenses;

    @Override
    public SearchType getSearchType() {
        return searchType;
    }
}
