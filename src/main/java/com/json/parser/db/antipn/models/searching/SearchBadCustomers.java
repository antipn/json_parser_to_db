package com.json.parser.db.antipn.models.searching;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchBadCustomers implements Search {

    SearchType searchType = SearchType.SEARCH_BAD_CUSTOMER;

    public SearchBadCustomers(Integer badCustomers) {
        this.badCustomers = badCustomers;
    }

    Integer badCustomers;

    @Override
    public SearchType getSearchType() {
        return searchType;
    }
}
