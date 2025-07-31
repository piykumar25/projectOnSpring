package com.code.ecom.mapper;

import com.code.ecom.dto.ProductRequest;
import com.code.ecom.dto.ProductResponse;
import com.code.ecom.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toProductResponse(Product product);


    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductRequest productRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateProductFromProductRequest(ProductRequest productRequest, @MappingTarget Product product);
}
