package com.spingBoot.shoppingCart.Service;

import com.spingBoot.shoppingCart.Entity.Cart;
import com.spingBoot.shoppingCart.Entity.UserEntity;
import com.spingBoot.shoppingCart.Exception.InsufficientStorageException;
import com.spingBoot.shoppingCart.Exception.ProductNotFoundException;
import com.spingBoot.shoppingCart.Exception.UserNotFoundException;
import com.spingBoot.shoppingCart.Model.AddToCard;
import com.spingBoot.shoppingCart.Model.CartItems;
import com.spingBoot.shoppingCart.Model.RemoveCartQuantity;
import com.spingBoot.shoppingCart.Model.UpdateCartItemQuantity;

import javax.naming.InsufficientResourcesException;
import java.util.List;

public interface CartService {


    void removeItemformCart(long userId, Long productId,int quantity) throws UserNotFoundException, ProductNotFoundException;


    List<CartItems> viewCardItems(long userId);


    Cart updateCartItemQuantity(long userId, UpdateCartItemQuantity updateCartItemQuantity) throws UserNotFoundException;

   void addToCart(long userId, AddToCard addToCard) throws UserNotFoundException, ProductNotFoundException, InsufficientStorageException;
}
