package com.spingBoot.shoppingCart.Service;

import com.spingBoot.shoppingCart.Entity.Product;
import com.spingBoot.shoppingCart.Exception.ProductNotFoundException;
import com.spingBoot.shoppingCart.Model.AddProduct;
import com.spingBoot.shoppingCart.Model.UpdateProductInfo;

import java.util.List;

public interface ProductService {

    List<Product> productList();

    Product save(AddProduct addProduct);

    void UpdateProductQuantity(Long id, int newQuantity);

    void deleteProduct(long id);


    List<Product> filterByCategoryAndName(String productName, String name) throws ProductNotFoundException;

    void updateProductInfo(UpdateProductInfo updateProductInfo) throws ProductNotFoundException;

    Product searchProductById(Long productId) throws ProductNotFoundException;


}
