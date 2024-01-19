package com.base.springsecurity.services.serviceImpl;

import com.base.springsecurity.exceptions.APIException;
import com.base.springsecurity.models.dto.category.CategoryResponse;
import com.base.springsecurity.models.dto.category.CategotyDTO;
import com.base.springsecurity.models.entity.Category;
import com.base.springsecurity.repository.CategoryRepository;
import com.base.springsecurity.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    //GetPaging
    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }

    @Override
    public CategotyDTO insertCategory(Category category) {
        //tim kiem category da ton tai chua
        Category savedCategory = repository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null) {
            throw new APIException("Category with the name '" + category.getCategoryName() + "' already existed!");
        }
        //Neu chua ton tai => Luu category moi
        savedCategory = repository.save(category);
        //Enity -> DTO
        return modelMapper.map(savedCategory, CategotyDTO.class);
    }

    @Override
    public CategotyDTO updateCategory(Long id, Category category) {
        return null;
    }

    @Override
    public String deleteCategory(Long id) {
        return null;
    }
}
