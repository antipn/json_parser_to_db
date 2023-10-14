package com.json.parser.db.antipn.mapper;

import com.json.parser.db.antipn.dto.ProductDto;
import com.json.parser.db.antipn.models.sqlObjects.ProductSQL;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    default ProductDto mapSQLToDto(ProductSQL entity) {
        ProductDto dto = new ProductDto();

        dto.setProductName(entity.getProductName());
        dto.setExpenses(Long.valueOf(entity.getExpenses()));
        return dto;
    }

}
