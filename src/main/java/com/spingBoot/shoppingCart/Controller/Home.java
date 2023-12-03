package com.spingBoot.shoppingCart.Controller;

import com.spingBoot.shoppingCart.Entity.Product;
import com.spingBoot.shoppingCart.Entity.UserEntity;
import com.spingBoot.shoppingCart.Model.UserRegisterModel;
import com.spingBoot.shoppingCart.Service.ProductService;
import com.spingBoot.shoppingCart.Service.UserEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Home {

    private ProductService productService;
    private UserEntityService userEntityService;

    public Home(ProductService productService, UserEntityService userEntityService) {
        this.productService = productService;
        this.userEntityService = userEntityService;
    }
    @PostMapping("/public/register")
    public ResponseEntity<String> save(@RequestBody UserRegisterModel user) throws RuntimeException {
        userEntityService.saveUser(user);
        return ResponseEntity.ok("successfully register");
    }

    @GetMapping("/public/products/all")
    public List<Product> productList() {
        return productService.productList();
    }

}
