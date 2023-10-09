package com.json.parser.db.antipn.models.searching;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
public class SearchByLastName implements Search {

    @JsonIgnore
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