package com.spingBoot.shoppingCart.Repository;

import com.spingBoot.shoppingCart.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Set<Product>findAllByIdIn(Set<Long>productIds);

    List<Product>findByNameContainingIgnoreCaseAndCategory_NameIgnoreCase(String name,String categoryName);

}
