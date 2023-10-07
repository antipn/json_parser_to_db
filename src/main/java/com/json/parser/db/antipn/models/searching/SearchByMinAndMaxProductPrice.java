package com.json.parser.db.antipn.models.searching;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class
SearchByMinAndMaxProductPrice implements Search {
    public SearchByMinAndMaxProductPrice(Integer minExpenses, Integer maxExpenses) {
        this.minExpenses = minExpenses;
        this.maxExpenses = maxExpenses;
    }

    SearchType searchType = SearchType.SEARCH_MIN_EXPENSES_AND_MAX_EXPENSES;

    Integer minExpenses;
    Integer maxExpenses;

    @Override
    public SearchType getSearchType() {
        return searchType;
    }
}
