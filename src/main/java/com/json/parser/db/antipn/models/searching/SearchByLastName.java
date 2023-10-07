package com.json.parser.db.antipn.models.searching;

import lombok.*;

@Getter
@Setter
@ToString
public class SearchByLastName implements Search {

    SearchType searchType = SearchType.SEARCH_CUSTOMER_BY_LASTNAME;

    public SearchByLastName(String lastName) {
        this.lastName = lastName;
    }

    public String lastName;

    @Override
    public SearchType getSearchType() {
        return searchType;
    }
}
