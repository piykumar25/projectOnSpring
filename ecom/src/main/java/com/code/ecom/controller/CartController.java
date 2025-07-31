package com.code.ecom.controller;

import com.code.ecom.dto.CartItemRequest;
import com.code.ecom.entity.CartItem;
import com.code.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/cart")
@RestController
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader(value = "X-User-ID", required = false) String userId,
            @RequestBody CartItemRequest request
    ) {
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.badRequest().body("User ID is required in X-User-ID header");
        }
        return cartService.addToCart(userId, request) ?
                ResponseEntity.status(HttpStatus.CREATED).build() :
                ResponseEntity.badRequest().body("Product Out of Stock or User Not Found or Product Not Found");

    }

    @DeleteMapping("items/{productId}")
    public ResponseEntity<Void> deleteCartItem(
            @RequestHeader(value = "X-User-ID", required = false) String userId,
            @PathVariable Long productId) {

        if (userId == null || userId.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return cartService.deleteCartItem(userId, productId) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems(
            @RequestHeader(value = "X-User-ID", required = false) String userId) {
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }
}
