package com.spingBoot.shoppingCart.Repository;

import com.spingBoot.shoppingCart.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryPrepository extends JpaRepository<Category,Long> {


    Optional<Category> findByNameContainingIgnoreCase(String name);
}
