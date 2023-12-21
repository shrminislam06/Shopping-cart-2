package com.spingBoot.shoppingCart.Controller;

import com.spingBoot.shoppingCart.Exception.UserNotFoundException;
import com.spingBoot.shoppingCart.Model.OrderHistory;
import com.spingBoot.shoppingCart.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/api/user/purchase")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> purchasedItems(@RequestParam long userId) throws RuntimeException, UserNotFoundException {
        orderService.purchasedItems(userId);
        return new ResponseEntity<>("purchase successfully", HttpStatus.OK);

    }

    @GetMapping("/api/user/order-history")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<OrderHistory>> getOrderHistory(@RequestParam long userId) throws UserNotFoundException {
        return ResponseEntity.ok(orderService.getOrderHistory(userId));
    }
}
