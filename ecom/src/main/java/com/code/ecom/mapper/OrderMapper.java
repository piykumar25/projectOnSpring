package com.code.ecom.mapper;

import com.code.ecom.dto.OrderResponse;
import com.code.ecom.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    OrderResponse toOrderResponse(Order order);
}
