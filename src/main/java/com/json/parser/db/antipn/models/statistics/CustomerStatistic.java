package com.json.parser.db.antipn.models.statistics;

import com.json.parser.db.antipn.dto.ProductDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CustomerStatistic {

    String name;
    List<ProductDto> purchases;
    Long totalExpenses = 0L;

    @Override
    public String toString() {
        return "CustomerStatistic{" +
                "name='" + name + '\'' +
                ", purchases=" + purchases +
                ", totalExpenses=" + totalExpenses +
                '}';
    }
}
