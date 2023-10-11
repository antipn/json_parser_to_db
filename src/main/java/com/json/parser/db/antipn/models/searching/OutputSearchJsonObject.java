package com.json.parser.db.antipn.models.searching;


import com.json.parser.db.antipn.dto.CustomerDto;
import com.json.parser.db.antipn.models.searching.Search;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OutputSearchJsonObject {

    Search criteria;

    List<CustomerDto> results;
}
