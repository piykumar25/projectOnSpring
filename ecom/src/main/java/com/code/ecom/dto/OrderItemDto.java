package com.code.ecom.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class OrderItemDto {

    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
}
