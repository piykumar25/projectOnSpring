package com.code.ecom.service;

import com.code.ecom.dto.CartItemRequest;
import com.code.ecom.entity.CartItem;
import com.code.ecom.entity.Product;
import com.code.ecom.entity.User;
import com.code.ecom.repository.CartItemRepository;
import com.code.ecom.repository.ProductRepository;
import com.code.ecom.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Service
public class CartServiceImpl implements  CartService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    @Override
    public boolean addToCart(String userId, CartItemRequest request) {

        Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));
        if (userOptional.isEmpty())
            return false;

        User user = userOptional.get();

        Optional<Product> productOptional = productRepository.findById(request.getProductId());
        if (productOptional.isEmpty())
            return false;

        Product product = productOptional.get();

        if (product.getStockQuantity() < request.getQuantity())
            return false;

       cartItemRepository.findByUserAndProduct(user, product)
                .ifPresentOrElse(cartItem -> {
                    cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
                    cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
                   cartItemRepository.save(cartItem);
                }, () -> {
                    CartItem cartItem = new CartItem();
                    cartItem.setUser(user);
                    cartItem.setProduct(product);
                    cartItem.setQuantity(request.getQuantity());
                    cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
                    cartItemRepository.save(cartItem);
                });

        return true;
    }

    @Override
    @Transactional
    public boolean deleteCartItem(String userId, Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return false;

        User user = userRepository.findById(Long.parseLong(userId)).orElse(null);
        if (user == null) return false;

        cartItemRepository.deleteByUserAndProduct(user, product);
        return true;
    }

    @Override
    public List<CartItem> getCartItems(String userId) {
        return userRepository.findById(Long.parseLong(userId)).map(cartItemRepository::findByUser).orElseGet(List::of);
    }

    @Override
    @Transactional
    public void clearCart(String userId) {
        userRepository.findById(Long.parseLong(userId))
                .ifPresent(cartItemRepository::deleteByUser);
    }
}
