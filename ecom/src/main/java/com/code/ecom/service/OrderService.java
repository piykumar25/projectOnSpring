package com.code.ecom.service;

import com.code.ecom.dto.OrderResponse;

import java.util.Optional;

public interface OrderService {
    Optional<OrderResponse> createOrder(String userId);
}
