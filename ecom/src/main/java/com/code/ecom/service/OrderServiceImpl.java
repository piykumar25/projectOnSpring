package com.code.ecom.service;

import com.code.ecom.dto.OrderResponse;
import com.code.ecom.entity.CartItem;
import com.code.ecom.entity.Order;
import com.code.ecom.entity.OrderItem;
import com.code.ecom.entity.User;
import com.code.ecom.enums.OrderStatus;
import com.code.ecom.mapper.OrderMapper;
import com.code.ecom.repository.OrderRepository;
import com.code.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements  OrderService {

    private final CartService cartService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Optional<OrderResponse> createOrder(String userId) {
        // validate for cart items
        List<CartItem> cartItems = cartService.getCartItems(userId);
        if (cartItems.isEmpty())
        {
            return Optional.empty();
        }
        // validate for user
        Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));
        if (userOptional.isEmpty())
        {
            return Optional.empty();
        }
        User user = userOptional.get();
        // Calculate total price
        BigDecimal totalPrice = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // create order
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalPrice);
        order.setStatus(OrderStatus.CONFIRMED);
        // create order items
        List<OrderItem> orderItems = cartItems.stream()
                .map(item ->
                    new OrderItem(null, item.getProduct(), item.getQuantity(), item.getPrice(), order)
                ).toList();
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        // clear the cart
        cartService.clearCart(userId);
        return Optional.of(orderMapper.toOrderResponse(savedOrder));
    }
}
