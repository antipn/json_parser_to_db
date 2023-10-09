package com.json.parser.db.antipn.models.searching;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SearchByProductNameAndQuantity implements Search {
    public SearchByProductNameAndQuantity(String productName, Integer minTimes) {
        this.productName = productName;
        this.minTimes = minTimes;
    }

    @JsonIgnore
    SearchType searchType = SearchType.SEARCH_PRODUCT_BY_NAME_AND_MIN_QUANTITY;

    String productName;
    Integer minTimes;

    @Override
    public SearchType getSearchType() {
        return searchType;
    }
}
