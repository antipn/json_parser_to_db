package com.json.parser.db.antipn.services;

import com.json.parser.db.antipn.models.sqlObjects.CustomerSQL;
import com.json.parser.db.antipn.models.sqlObjects.ProductSQL;
import com.json.parser.db.antipn.models.sqlObjects.WorkingDaysSQL;
import com.json.parser.db.antipn.repositories.CustomerRepository;
import com.json.parser.db.antipn.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public StatisticsService(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public void updateW_daysTable(String startDate, String endDate) {
        customerRepository.deleteAllRecordsFromWDaysTable(); //чистим таблицу
        customerRepository.fillingWDaysTable(startDate, endDate); //заполняем таблицу рабочими днями

    }

    public List<CustomerSQL> getCustomersId() {
        Optional<List<CustomerSQL>> list = customerRepository.getCustomerForStatistics();
        return list.isPresent() ? list.get() : null;
    }

    public List<ProductSQL> getCustomerPurchasesStatistics(Long customerId) {
        Optional<List<ProductSQL>> customerStatistics = customerRepository.getStatisticByCystomerId(customerId);
        return customerStatistics.isPresent() ? customerStatistics.get() : null;
    }

    public WorkingDaysSQL getTotalWorkingDays() {
        Optional<WorkingDaysSQL> totalWorkingDays = customerRepository.getTotalWorkingDays();
        return totalWorkingDays.isPresent() ? totalWorkingDays.get() : null;
    }

}
