package com.base.springsecurity.service.serviceImpl;

import com.base.springsecurity.exception.ResourceNotFoundException;
import com.base.springsecurity.model.dto.catalog.category.CategoryDTO;
import com.base.springsecurity.model.entity.Category;
import com.base.springsecurity.repository.CategoryRepository;
import com.base.springsecurity.service.CategoryService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    RedisTemplate redisTemplate;

    private Gson gson = new Gson();

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        //Khi xu ly lan dau tien, se len Redis server lay data
        String dataRedis = (String) redisTemplate.opsForValue().get("categories");
        //Neu dataRedis tren server khong co gia tri
        if (dataRedis == null) {
            System.out.println("Chưa có dữ liệu!");
            //Map DTO -> Entity
            List<Category> categoryList = categoryRepository.findAll();
            //Map Entity -> DTO
            categoryDTOList = categoryList
                    .stream()
                    .map(category ->
                            modelMapper.map(category, CategoryDTO.class))
                    .collect(Collectors.toList());

            //parse kieu du kieu tu Object -> String
            String dataJson = gson.toJson(categoryDTOList);
            redisTemplate.opsForValue().set("categories", dataJson);
        } else {
            Type listType = new TypeToken<List<CategoryDTO>>(){}.getType();
            //part kieu du lieu tu String -> Json
            categoryDTOList = gson.fromJson(dataRedis, listType);
            System.out.println("Có dữ liệu!");
        }
        return categoryDTOList;
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

    //Return list products by categoryName
    @Override
    public CategoryDTO findCategoryByName(String categoryName) {
        return null;
    }
}
