package com.spingBoot.shoppingCart.Controller;
import com.spingBoot.shoppingCart.Entity.Cart;
import com.spingBoot.shoppingCart.Exception.InsufficientStorageException;
import com.spingBoot.shoppingCart.Exception.ProductNotFoundException;
import com.spingBoot.shoppingCart.Exception.UserNotFoundException;
import com.spingBoot.shoppingCart.Model.AddToCard;
import com.spingBoot.shoppingCart.Model.CartItems;
import com.spingBoot.shoppingCart.Model.UpdateCartItemQuantity;
import com.spingBoot.shoppingCart.Service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CartItemController {
    private final CartService cartService;

    public CartItemController(CartService cartService) {
        this.cartService = cartService;

    }

    @PostMapping("api/user/addtocart")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN') or hasAnyAuthority('ROLE_USER')")

    public ResponseEntity<String> addToCart(@RequestParam long userId, @RequestBody AddToCard addToCard) throws UserNotFoundException, ProductNotFoundException, InsufficientStorageException {
        cartService.addToCart(userId, addToCard);
        return ResponseEntity.ok("successfully added");

    }

    @GetMapping("api/user/view-cart")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN') or hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<List<CartItems>> viewCardItems(@RequestParam long userId) throws UserNotFoundException {
        return ResponseEntity.ok(cartService.viewCardItems(userId));
    }


    @PostMapping("api/user/remove-quantity")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")

    public ResponseEntity<String> removeItemformCart(@RequestParam long userId, @RequestParam Long productId, @RequestParam int quantity) throws UserNotFoundException, ProductNotFoundException {
        cartService.removeItemformCart(userId, productId, quantity);
        return ResponseEntity.ok("removed quantity."+quantity);
    }

    @PutMapping("api/user/update-cart-quantity")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")

    public ResponseEntity<String> updateCartItemQuantity(@RequestParam long userId, @RequestBody UpdateCartItemQuantity updateCartItemQuantity) throws UserNotFoundException {
        Cart updateCart = cartService.updateCartItemQuantity(userId, updateCartItemQuantity);
        if (updateCart != null) {
            return new ResponseEntity<>("update quantity successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cart item not found", HttpStatus.BAD_REQUEST);
        }

    }


}
