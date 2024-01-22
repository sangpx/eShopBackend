package com.base.springsecurity.services.serviceImpl;

import com.base.springsecurity.exceptions.APIException;
import com.base.springsecurity.exceptions.ResourceNotFoundException;
import com.base.springsecurity.models.dto.catalog.category.CategoryDTO;
import com.base.springsecurity.models.entity.Category;
import com.base.springsecurity.repository.CategoryRepository;
import com.base.springsecurity.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        //Map DTO -> Entity
        List<Category> categoryList = categoryRepository.findAll();
        //Map Entity -> DTO
       return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        //Tim kiem Category xem da ton tai chua
        Category category =  categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        //Entity -> DTO
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO insertCategory(CategoryDTO categoryDTO) {
        //DTO -> Entity
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);
        //Entity -> DTO
        return modelMapper.map(savedCategory, CategoryDTO.class);
     }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {
        //Tim kiem Category xem da ton tai chua
       Category category =  categoryRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
       //Tien hanh update Category entity
       category.setName(categoryDTO.getName());
       category.setLevel(category.getLevel());
       category.setId(id);
       Category updatedCategory = categoryRepository.save(category);

       //Entity -> DTO
       return modelMapper.map(updatedCategory, CategoryDTO.class);
    }


    @Override
    public void deleteCategory(Long id) {
        //Tim kiem Category xem da ton tai chua
        Category category =  categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO findCategoryByName(String categoryName) {
        return null;
    }
}
