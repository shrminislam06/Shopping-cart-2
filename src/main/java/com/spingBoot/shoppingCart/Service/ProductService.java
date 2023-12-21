package com.spingBoot.shoppingCart.Service;

import com.spingBoot.shoppingCart.Entity.Product;
import com.spingBoot.shoppingCart.Exception.ProductNotFoundException;
import com.spingBoot.shoppingCart.Model.AddProduct;
import com.spingBoot.shoppingCart.Model.UpdateProductInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> productList(Pageable pageable);

    Product save(AddProduct addProduct);

    void UpdateProductQuantity(Long id, int newQuantity);

    void deleteProduct(long id);


    List<Product> filterByCategoryAndName(String productName, String name) throws ProductNotFoundException;

    void updateProductInfo(UpdateProductInfo updateProductInfo) throws ProductNotFoundException;

    Product searchProductById(Long productId) throws ProductNotFoundException;


    List<Product> filterBypriceInAsc(String productName)throws  ProductNotFoundException;
}
