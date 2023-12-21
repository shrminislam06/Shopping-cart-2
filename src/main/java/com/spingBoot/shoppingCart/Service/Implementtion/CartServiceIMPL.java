package com.spingBoot.shoppingCart.Service.Implementtion;

import com.spingBoot.shoppingCart.Entity.Cart;
import com.spingBoot.shoppingCart.Entity.Product;
import com.spingBoot.shoppingCart.Entity.UserEntity;
import com.spingBoot.shoppingCart.Exception.InsufficientStorageException;
import com.spingBoot.shoppingCart.Exception.ProductNotFoundException;
import com.spingBoot.shoppingCart.Exception.UserNotFoundException;
import com.spingBoot.shoppingCart.Model.AddToCard;
import com.spingBoot.shoppingCart.Model.CartItems;
import com.spingBoot.shoppingCart.Model.UpdateCartItemQuantity;
import com.spingBoot.shoppingCart.Repository.CartRepository;
import com.spingBoot.shoppingCart.Repository.ProductRepository;
import com.spingBoot.shoppingCart.Repository.UserRepository;
import com.spingBoot.shoppingCart.Service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceIMPL implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartServiceIMPL(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void removeItemformCart(long userId, Long productId, int quantity) throws UserNotFoundException, ProductNotFoundException {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<Cart> optionalCart = cartRepository.findByProductsIdAndPurchasedFalse(productId);
            if (optionalCart.isPresent()) {
                Cart cart = optionalCart.get();
                if (cart.getQuantity() < quantity) {
                    throw new RuntimeException("remove quantity not found in cart");
                } else {
                    if (cart.getQuantity() == quantity) {
                        cartRepository.delete(cart);

                    } else {
                        cart.setQuantity(cart.getQuantity() - quantity);
                        cart.setTotalPrice(cart.getProducts().getPrice() * cart.getQuantity());
                        cartRepository.save(cart);
                    }
                }
            } else {
                throw new RuntimeException("Cart item not found");
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<CartItems> viewCardItems(long userId) {
        List<Cart> cartList = cartRepository.findByUserEntityId(userId);
        return cartList.stream().map(cart -> new CartItems(cart.getProducts().getId(), cart.getProducts().getName(), cart.getQuantity(), cart.getTotalPrice())).collect(Collectors.toList());
    }

    @Override
    public Cart updateCartItemQuantity(long userId, UpdateCartItemQuantity updateCartItemQuantity) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Long productId = updateCartItemQuantity.getProductId();
            int updatedQuantity = updateCartItemQuantity.getNewQuantity();
            Optional<Cart> optionalCart = cartRepository.findByUserEntityIdAndProductsId(userId, productId);
            Cart cartItem = optionalCart.get();
            cartItem.setQuantity(cartItem.getQuantity() + updatedQuantity);
            cartItem.setTotalPrice(cartItem.getProducts().getPrice() * cartItem.getQuantity());
            return cartRepository.save(cartItem);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void addToCart(long userId, AddToCard addToCard) throws UserNotFoundException, ProductNotFoundException, InsufficientStorageException {
        long productId = addToCard.getProductId();
        int quantity = addToCard.getQuantity();

        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        Optional<Cart> existingCartItem = cartRepository.findByProductsIdAndPurchasedFalse(productId);
        if (product.getStock() >= quantity) {
            if (existingCartItem.isPresent()) {
                Cart cartItem = existingCartItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
                cartRepository.save(cartItem);

            } else {
                Cart newCartItem = new Cart();
                newCartItem.setUserEntity(user);
                newCartItem.setProducts(product);
                newCartItem.setQuantity(quantity);
                newCartItem.setTotalPrice(product.getPrice() * quantity);
                cartRepository.save(newCartItem);
            }
        } else {
            throw new InsufficientStorageException();
        }
    }
}


