package com.base.springsecurity.controllers;


import com.base.springsecurity.models.dto.category.CategotyDTO;
import com.base.springsecurity.models.entity.Category;
import com.base.springsecurity.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("/admin/createCategory")
    public ResponseEntity<CategotyDTO> createCategory(@Valid @RequestBody Category request) {
        CategotyDTO saveCategoryDto = service.insertCategory(request);
        return new ResponseEntity<CategotyDTO>(saveCategoryDto, HttpStatus.CREATED);
    }

}
