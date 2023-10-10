package com.json.parser.db.antipn.services;

import com.json.parser.db.antipn.dto.CustomerDto;
import com.json.parser.db.antipn.mapper.CustomerMapper;
import com.json.parser.db.antipn.models.Customer;
import com.json.parser.db.antipn.models.Product;
import com.json.parser.db.antipn.repositories.CustomerRepository;
import com.json.parser.db.antipn.repositories.ProductRepository;
import com.json.parser.db.antipn.repositories.PurchaseRepository;
import com.json.parser.db.antipn.models.sqlObjects.CustomerSQL;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class SearchService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;


    public SearchService(CustomerRepository customerRepository, ProductRepository productRepository, PurchaseRepository purchaseRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public List<CustomerDto> searchCustomerByLastName(String lastName) {

        Optional<List<Customer>> request = customerRepository.findAllByLastName(lastName);

        if (request.isPresent()) {
            return request.get().stream().map(CustomerMapper.CUSTOMER_MAPPER::mapToDto).toList();
        }

        return null;
    }

    public List<CustomerDto> searchProductNameAndMinTimes(String productName, Long minTimes) {
        Optional<Product> productByProductName = productRepository.findProductByProductName(productName);

        List<CustomerSQL> result = new ArrayList<>();

        if (productByProductName.isPresent()) {
            Optional<List<CustomerSQL>> request = purchaseRepository.findMinTimes(productByProductName.get().getId(), minTimes);
            if (request.isPresent()) {
                result = request.get();
            }
        }


        return result.stream()
                .map(CustomerMapper.CUSTOMER_MAPPER::mapSQLToDto)
                .toList();

    }

    public List<CustomerDto> searchCustomersWithPurchasesBeetwenMinMax(Long min, Long max) {

        Optional<List<CustomerSQL>> request = purchaseRepository.findCustomersByPurchasingPriceBetweenMinAndMax(min, max);

        return request.map(customerSQLS -> customerSQLS.stream()
                .map(CustomerMapper.CUSTOMER_MAPPER::mapSQLToDto)
                .toList()).orElse(null);

    }

    public List<CustomerDto> searchBadCustomers(Long num) {

        Optional<List<CustomerSQL>> request = customerRepository.findBadCustomers(num);

        return request.map(customerSQLS -> customerSQLS.stream()
                .map(CustomerMapper.CUSTOMER_MAPPER::mapSQLToDto)
                .toList()).orElse(null);
    }
}
