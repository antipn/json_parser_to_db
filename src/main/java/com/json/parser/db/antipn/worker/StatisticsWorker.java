package com.json.parser.db.antipn.worker;

import com.json.parser.db.antipn.dto.ProductDto;
import com.json.parser.db.antipn.mapper.ProductMapper;
import com.json.parser.db.antipn.models.sqlObjects.CustomerSQL;
import com.json.parser.db.antipn.models.sqlObjects.ProductSQL;
import com.json.parser.db.antipn.models.statistics.OutputStatisticsJsonObject;
import com.json.parser.db.antipn.models.statistics.CustomerStatistic;
import com.json.parser.db.antipn.services.StatisticsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatisticsWorker {

    private final StatisticsService statisticsService;

    public StatisticsWorker(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public OutputStatisticsJsonObject preparingOutputData(String startDate, String endDate) {

        //подготавливаем таблицу с рабочими днями
        statisticsService.updateW_daysTable(startDate, endDate);

        //получили Ids всех покупателей за все рабочие дни
        List<CustomerSQL> customers = statisticsService.getCustomersId();


        List<CustomerStatistic> customerStatistics = new ArrayList<>();
        if (customers != null) {
            //по каждому покупателю вычленяем его статистику покупок
            for (CustomerSQL customer : customers) {
                //получаем статистику по продуктам покупателя в SQL
                List<ProductSQL> customerPurchasesStatisticsSQL = statisticsService.getCustomerPurchasesStatistics(customer.getId());
                //получаем статистику по продуктам покупателя в DTO
                List<ProductDto> customerPurchasesStatistics = customerPurchasesStatisticsSQL.stream().map(ProductMapper.PRODUCT_MAPPER::mapSQLToDto).toList();
                //  статистика по покупкам может быть пустой (но мы бы не получили тогда id покупателя)

                //наполняем статистику для покупателя //возможно лучше это делать через вызов метода
                CustomerStatistic customerStatistic = new CustomerStatistic();

                customerStatistic.setName(customer.getFirstName() + " " + customer.getLastName()); //имя + фамилию
                customerStatistic.setPurchases(customerPurchasesStatistics); //список покупок со стоимостью
                customerPurchasesStatistics.forEach(item -> customerStatistic.setTotalExpenses(customerStatistic.getTotalExpenses() + item.getExpenses())); // totalExpenses для покупателя

                //положили статистику покупателя в лист
                customerStatistics.add(customerStatistic);

            }
        } else {
            System.out.println("Выкинуть ошибку ");
        }

        //формируем общую статистику
        OutputStatisticsJsonObject outputStatisticsJsonObject = new OutputStatisticsJsonObject();
        //устанавливаем кол-во рабочих дней в периоде
        outputStatisticsJsonObject.setTotalDays(statisticsService.getTotalWorkingDays().getWorkingDays());
        outputStatisticsJsonObject.setCustomers(customerStatistics);

        Long totalExpenses = customerStatistics.stream().mapToLong(CustomerStatistic::getTotalExpenses)
                .sum();

        Long avgExpenses = 0L;

        //проверка на деление при пустом массиве пользователей
        if (outputStatisticsJsonObject.getCustomers().size() != 0) {
            avgExpenses = totalExpenses / customerStatistics.size();
        }

        outputStatisticsJsonObject.setTotalExpenses(totalExpenses);
        outputStatisticsJsonObject.setAvgExpenses(avgExpenses);

        //System.out.println(outputStatisticsJsonObject.toString());

        return outputStatisticsJsonObject;
    }
}
