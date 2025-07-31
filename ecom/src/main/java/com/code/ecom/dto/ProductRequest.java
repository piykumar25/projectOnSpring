package com.code.ecom.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Integer StockQuantity;
    private String category;
}
