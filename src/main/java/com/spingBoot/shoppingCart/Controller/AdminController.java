package com.spingBoot.shoppingCart.Controller;

import com.spingBoot.shoppingCart.Entity.Product;
import com.spingBoot.shoppingCart.Entity.UserEntity;
import com.spingBoot.shoppingCart.Exception.ProductNotFoundException;
import com.spingBoot.shoppingCart.Exception.UserNotFoundException;
import com.spingBoot.shoppingCart.Model.AddProduct;
import com.spingBoot.shoppingCart.Model.UpdateProductInfo;
import com.spingBoot.shoppingCart.Model.UpdateUserInfo;
import com.spingBoot.shoppingCart.Service.CartService;
import com.spingBoot.shoppingCart.Service.CategoryService;
import com.spingBoot.shoppingCart.Service.ProductService;
import com.spingBoot.shoppingCart.Service.UserEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserEntityService userEntityService;
    private final ProductService productService;

    public AdminController(UserEntityService userEntityService, ProductService productService, CategoryService categoryService, CartService cartService) {
        this.userEntityService = userEntityService;
        this.productService = productService;
    }
    @GetMapping("/getUserByUsername")
    public ResponseEntity<UserEntity> getUserByUsername(@RequestParam String username) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userEntityService.getUserByUsername(username));
    }

    @GetMapping("/userList")
    public ResponseEntity<List<UserEntity>> getAll() {
        return ResponseEntity.ok(userEntityService.getAll());
    }

    @GetMapping("/search-product-by/{productId}")
    public ResponseEntity<Product> searchProductById(@PathVariable Long productId) throws ProductNotFoundException {
        System.out.println("hello this is a product");
        return ResponseEntity.ok(productService.searchProductById(productId));
    }

    @PutMapping("/update-user-info")
    public ResponseEntity<String> updateUserInfo(@RequestBody UpdateUserInfo updateUserInfo) throws UserNotFoundException {
        userEntityService.updateUserInfo(updateUserInfo);
        return ResponseEntity.ok("your information updated successfully");
    }

    @DeleteMapping("/delete-userby-userId")
    public ResponseEntity<String> deleteUser(@RequestParam long userId) {
        userEntityService.deleteUser(userId);
        return ResponseEntity.ok("deleted id :" + userId);
    }


    @PostMapping("/product/save")
    public ResponseEntity<Product> addProduct(@RequestBody AddProduct addProduct) {
        return ResponseEntity.ok().body(productService.save(addProduct));
    }

    @PutMapping("/updateQuantity/{productId}")
    public ResponseEntity<String> updateQuantityInStock(@PathVariable long productId, @RequestParam int newQuantity) {
        productService.UpdateProductQuantity(productId, newQuantity);
        return ResponseEntity.ok("quantity update successfully...!");
    }

    @PutMapping("/update-product")
    public ResponseEntity<String> updateProductInfo(@RequestBody UpdateProductInfo updateProductInfo) throws ProductNotFoundException {
        productService.updateProductInfo(updateProductInfo);
        return ResponseEntity.ok("Product updated successfully..");

    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Deleted item " + productId);
    }

}
