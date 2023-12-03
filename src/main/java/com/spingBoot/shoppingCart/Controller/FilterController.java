package com.spingBoot.shoppingCart.Controller;

import com.spingBoot.shoppingCart.Entity.Category;
import com.spingBoot.shoppingCart.Entity.Product;
import com.spingBoot.shoppingCart.Repository.CategoryPrepository;
import com.spingBoot.shoppingCart.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

public class FilterController {
    private final ProductService productService;
    private final CategoryPrepository categoryPrepository;

    public FilterController(ProductService productService, CategoryPrepository categoryPrepository) {
        this.productService = productService;
        this.categoryPrepository = categoryPrepository;
    }

    @GetMapping("/api/user/product/filterByNameAndCategory")
    public ResponseEntity<List<Product>> filterByNameAndCategory(@RequestParam String productName, @RequestParam String categoryName) throws Exception {
        Optional<Category> cat = categoryPrepository.findByNameContainingIgnoreCase(categoryName);
        if (cat.isPresent()) {
            List<Product> products = productService.filterByCategoryAndName(productName, cat.get().getName());
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
