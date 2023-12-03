package com.spingBoot.shoppingCart.Service;

import com.spingBoot.shoppingCart.Exception.UserNotFoundException;
import com.spingBoot.shoppingCart.Model.OrderHistory;

import java.util.List;

public interface OrderService {
    String purchasedItems(long userId) throws UserNotFoundException;


    List<OrderHistory> getOrderHistory(long userId);
}
