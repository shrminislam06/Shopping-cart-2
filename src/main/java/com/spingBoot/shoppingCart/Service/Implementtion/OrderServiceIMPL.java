package com.spingBoot.shoppingCart.Service.Implementtion;

import com.spingBoot.shoppingCart.Entity.Cart;
import com.spingBoot.shoppingCart.Model.OrderHistory;
import com.spingBoot.shoppingCart.Repository.CartRepository;
import com.spingBoot.shoppingCart.Repository.ProductRepository;
import com.spingBoot.shoppingCart.Repository.UserRepository;
import com.spingBoot.shoppingCart.Service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceIMPL implements OrderService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public OrderServiceIMPL(UserRepository userRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public String purchasedItems(long userId) {
        List<Cart> cartItems = cartRepository.findByUserEntityId(userId);
        if (cartItems.isEmpty()) {
           return "Your cart is empty";
        }
        for (Cart cart : cartItems) {
            cart.setPurchased(true);
            cart.setPurchaseDate(new Date());
        }
        cartRepository.saveAll(cartItems);

        return "Purchased Succesfully";
    }

    @Override
    public List<OrderHistory> getOrderHistory(long userId) {
        List<Cart> cartList = cartRepository.findByUserEntityId(userId);
        List<OrderHistory> orderHistories = cartList.stream().map(order -> new OrderHistory(order.getId(), order.getProducts().getId(), order.getProducts().getName(), order.getQuantity(), order.getTotalPrice(), order.getPurchaseDate())).collect(Collectors.toList());
        return orderHistories;
    }
}
