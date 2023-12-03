package com.spingBoot.shoppingCart.Service.Implementtion;

import com.spingBoot.shoppingCart.Entity.Category;
import com.spingBoot.shoppingCart.Model.UpdateCategory;
import com.spingBoot.shoppingCart.Repository.CategoryPrepository;
import com.spingBoot.shoppingCart.Service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceIMPL implements CategoryService {
    private final CategoryPrepository categoryPrepository;

    public CategoryServiceIMPL(CategoryPrepository categoryPrepository) {
        this.categoryPrepository = categoryPrepository;
    }

    @Override
    public Category save(Category category) {
        return categoryPrepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryPrepository.findAll();
    }

    @Override
    public Category updateCategoryName(UpdateCategory updateCategory) {
        Category category = categoryPrepository.findById(updateCategory.getId()).orElseThrow(() -> new RuntimeException("category id not found " + updateCategory.getId()));
        category.setName(updateCategory.getName());
        return category;
    }

    @Override
    public void deleteCategory(long id) {
        categoryPrepository.deleteById(id);
    }
}
