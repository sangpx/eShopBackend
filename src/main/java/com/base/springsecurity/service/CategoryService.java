package com.base.springsecurity.service;

import com.base.springsecurity.model.dto.catalog.category.CategoryDTO;

import java.util.List;

public interface CategoryService {

    //For Admin
    CategoryDTO insertCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);
    void deleteCategory(Long id);

    //For Both Admin And User
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO findCategoryByName(String categoryName);
}
