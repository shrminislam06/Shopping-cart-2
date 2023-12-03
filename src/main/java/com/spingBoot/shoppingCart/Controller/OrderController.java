package com.spingBoot.shoppingCart.Controller;

import com.spingBoot.shoppingCart.Exception.UserNotFoundException;
import com.spingBoot.shoppingCart.Model.OrderHistory;
import com.spingBoot.shoppingCart.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/api/user/purchase/{userId}")
    public ResponseEntity<String> purchasedItems(@PathVariable long userId) throws RuntimeException, UserNotFoundException {
        orderService.purchasedItems(userId);
        return new ResponseEntity<>("purchase successfully", HttpStatus.OK);

    }

    @GetMapping("/api/user/order-hostory/{userId}")
    public ResponseEntity<List<OrderHistory>> getOrderHistory(@PathVariable long userId) throws UserNotFoundException {
        return ResponseEntity.ok(orderService.getOrderHistory(userId));
    }
}
