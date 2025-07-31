package com.code.ecom.service;

import com.code.ecom.dto.CartItemRequest;
import com.code.ecom.entity.CartItem;

import java.util.List;

public interface CartService {

    boolean addToCart(String userId, CartItemRequest request);

    boolean deleteCartItem(String userId, Long productId);

    List<CartItem> getCartItems(String userId);

    void clearCart(String userId);
}
