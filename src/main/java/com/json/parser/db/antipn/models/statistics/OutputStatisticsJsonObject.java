package com.json.parser.db.antipn.models.statistics;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
@Getter
@Setter
public class OutputStatisticsJsonObject {

    String type = "stat";
    Integer totalDays;
    List<CustomerStatistic> customers;
    Long totalExpenses;
    Long avgExpenses;

    @Override
    public String toString() {
        return "OutputStatisticsJsonObject{" +
                "type='" + type + '\'' +
                ", totalDays=" + totalDays +
                ", customers=" + customers +
                ", totalExpenses=" + totalExpenses +
                ", avgExpenses=" + avgExpenses +
                '}';
    }
}
