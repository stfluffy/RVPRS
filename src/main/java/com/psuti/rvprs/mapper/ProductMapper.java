package com.psuti.rvprs.mapper;

import com.psuti.rvprs.dto.ProductDto;
import com.psuti.rvprs.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author Modenov D.A.
 */

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "quantity", source = "quantity"),
            @Mapping(target = "shelf", source = "shelf")
    })
    ProductDto toDto(Product product);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "quantity", source = "quantity"),
            @Mapping(target = "shelf", source = "shelf")
    })
    Product toModel(ProductDto productDto);


}
