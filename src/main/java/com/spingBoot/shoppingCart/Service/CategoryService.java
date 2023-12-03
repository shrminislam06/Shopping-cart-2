package com.spingBoot.shoppingCart.Service;

import com.spingBoot.shoppingCart.Entity.Category;
import com.spingBoot.shoppingCart.Model.UpdateCategory;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    List<Category> getAllCategory();

    Category updateCategoryName(UpdateCategory updateCategory);

    void deleteCategory(long id);

}
