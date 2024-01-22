package com.base.springsecurity.services;

import com.base.springsecurity.models.dto.catalog.category.CategoryDTO;
import com.base.springsecurity.models.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO insertCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);
    void deleteCategory(Long id);
    CategoryDTO findCategoryByName(String categoryName);
}
