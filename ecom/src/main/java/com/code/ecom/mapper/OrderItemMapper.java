package com.code.ecom.mapper;

import com.code.ecom.dto.OrderItemDto;
import com.code.ecom.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {


    @Mapping(target = "subTotal",
            expression = "java(orderItem.getPrice().multiply(new java.math.BigDecimal(orderItem.getQuantity())))")
    @Mapping(target = "productId",
            expression = "java(orderItem.getProduct().getId())")
    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);
}
