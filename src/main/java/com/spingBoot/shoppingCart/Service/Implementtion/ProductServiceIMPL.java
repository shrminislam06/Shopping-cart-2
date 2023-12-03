package com.spingBoot.shoppingCart.Service.Implementtion;

import com.spingBoot.shoppingCart.Entity.Category;
import com.spingBoot.shoppingCart.Entity.Product;
import com.spingBoot.shoppingCart.Exception.CategoryNotFoundException;
import com.spingBoot.shoppingCart.Exception.ProductNotFoundException;
import com.spingBoot.shoppingCart.Model.AddProduct;
import com.spingBoot.shoppingCart.Model.UpdateProductInfo;
import com.spingBoot.shoppingCart.Repository.CategoryPrepository;
import com.spingBoot.shoppingCart.Repository.ProductRepository;
import com.spingBoot.shoppingCart.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIMPL implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryPrepository categoryPrepository;

    public ProductServiceIMPL(ProductRepository productRepository, CategoryPrepository categoryPrepository) {
        this.productRepository = productRepository;
        this.categoryPrepository = categoryPrepository;
    }

    @Override
    public List<Product> productList() {
        return productRepository.findAll();
    }


    @Override
    public Product save(AddProduct addProduct) {
        Category category = categoryPrepository.findById(addProduct.getCatId()).orElseThrow(() -> new CategoryNotFoundException());
        Product product = new Product();
        product.setId(addProduct.getId());
        product.setName(addProduct.getName());
        product.setPrice(addProduct.getPrice());
        product.setStock(addProduct.getStock());
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

    @Override
    public void UpdateProductQuantity(Long id, int newQuantity) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            Product product1 = product.get();
            product1.setStock(product1.getStock() + newQuantity);
            productRepository.save(product1);
        }


    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> filterByCategoryAndName(String name, String categoryName) throws ProductNotFoundException {
        if (productRepository.findByNameContainingIgnoreCaseAndCategory_NameIgnoreCase(name, categoryName).isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            return productRepository.findByNameContainingIgnoreCaseAndCategory_NameIgnoreCase(name, categoryName);
        }
    }

    @Override
    public void updateProductInfo(UpdateProductInfo updateProductInfo) throws ProductNotFoundException {
        Product product=productRepository.findById(updateProductInfo.getId()).get();
        System.out.println(updateProductInfo.getId());
        if(product!=null){
            product.setName(updateProductInfo.getName());
            product.setPrice(updateProductInfo.getPrice());
            productRepository.save(product);
        }else {
            throw  new ProductNotFoundException();
        }
    }

    @Override
    public Product searchProductById(Long productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }


}
