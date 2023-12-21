package com.spingBoot.shoppingCart.Controller;

import com.spingBoot.shoppingCart.Entity.Product;
import com.spingBoot.shoppingCart.Exception.ProductNotFoundException;
import com.spingBoot.shoppingCart.Model.AddProduct;
import com.spingBoot.shoppingCart.Model.UpdateProductInfo;
import com.spingBoot.shoppingCart.Service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping("/public/products/all")
    public Page<Product> productList(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(pageNo, size);

        return productService.productList(pageRequest);
    }


    @GetMapping("/search-product-by/{productId}")
    public ResponseEntity<Product> searchProductById(@PathVariable Long productId) throws ProductNotFoundException {
        System.out.println("hello this is a product");
        return ResponseEntity.ok(productService.searchProductById(productId));
    }


    @PostMapping("/product/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Product> addProduct(@RequestBody AddProduct addProduct) {
        return ResponseEntity.ok().body(productService.save(addProduct));
    }

    @PutMapping("/updateQuantity")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateQuantityInStock(@RequestParam long productId, @RequestParam int newQuantity) {
        productService.UpdateProductQuantity(productId, newQuantity);
        return ResponseEntity.ok("quantity update successfully...!");
    }

    @PutMapping("/update-product")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateProductInfo(@RequestBody UpdateProductInfo updateProductInfo) throws ProductNotFoundException {
        productService.updateProductInfo(updateProductInfo);
        return ResponseEntity.ok("Product updated successfully..");

    }

    @DeleteMapping("/deleteProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteProduct(@RequestParam long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Deleted item " + productId);
    }

}
