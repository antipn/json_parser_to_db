package com.json.parser.db.antipn.services;

import com.json.parser.db.antipn.dto.CustomerDto;
import com.json.parser.db.antipn.mapper.CustomerMapper;
import com.json.parser.db.antipn.models.Customer;
import com.json.parser.db.antipn.repositories.CommonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CommonService {

    private final CommonRepository commonRepository;


    public CommonService(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
    }

    public List<CustomerDto> searchCustomerByLastName(String lastName) {

        Optional<List<Customer>> request = commonRepository.findAllByLastName(lastName);

        return request.map(customers -> customers.stream()
                .map(CustomerMapper.CUSTOMER_MAPPER::mapToDto)
                .collect(Collectors.toList())).orElse(null);

    }
}
