package com.code.ecom.controller;


import com.code.ecom.dto.OrderResponse;
import com.code.ecom.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Repository
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestHeader("X-User-ID") String userId) {

        return orderService.createOrder(userId)
                .map(orderResponse -> ResponseEntity.status(HttpStatus.CREATED).body(orderResponse))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
