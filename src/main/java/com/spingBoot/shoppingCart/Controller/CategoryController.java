package com.spingBoot.shoppingCart.Controller;

import com.spingBoot.shoppingCart.Entity.Category;
import com.spingBoot.shoppingCart.Model.UpdateCategory;
import com.spingBoot.shoppingCart.Service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/api/user/category")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @PostMapping("/category/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    @PutMapping("/api/categoy/update")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Category updatecategory(@RequestBody UpdateCategory updateCategory) {
        return categoryService.updateCategoryName(updateCategory);
    }

    @DeleteMapping("/api/category/delete")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCategory(@RequestParam long id) {
        categoryService.deleteCategory(id);
    }
}
