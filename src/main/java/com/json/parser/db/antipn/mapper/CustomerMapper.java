package com.json.parser.db.antipn.mapper;

import com.json.parser.db.antipn.dto.CustomerDto;
import com.json.parser.db.antipn.models.Customer;
import com.json.parser.db.antipn.sqlObjects.CustomerSQL;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);


    CustomerDto mapToDto(Customer entity);

    CustomerDto mapToDto(CustomerSQL entity);


}
