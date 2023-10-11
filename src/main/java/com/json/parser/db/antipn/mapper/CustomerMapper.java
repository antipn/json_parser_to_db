package com.json.parser.db.antipn.mapper;

import com.json.parser.db.antipn.dto.CustomerDto;
import com.json.parser.db.antipn.models.entity.Customer;
import com.json.parser.db.antipn.models.sqlObjects.CustomerSQL;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);


    CustomerDto mapToDto(Customer entity);

    default CustomerDto mapSQLToDto(CustomerSQL entity) {
        CustomerDto dto = new CustomerDto();

        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());

        return dto;
    }
}
