package com.base.springsecurity.services;

import com.base.springsecurity.models.dto.category.CategoryResponse;
import com.base.springsecurity.models.dto.category.CategotyDTO;
import com.base.springsecurity.models.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategotyDTO insertCategory(Category category);
    CategotyDTO updateCategory(Long id, Category category);
    String deleteCategory(Long id);

}
