package com.base.springsecurity.controllers;

import com.base.springsecurity.models.dto.catalog.category.CategoryDTO;
import com.base.springsecurity.models.entity.Category;
import com.base.springsecurity.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    //DI CategoryService
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping("/admin/insertCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> insertCategory(@Valid @RequestBody CategoryDTO request) {
            CategoryDTO savedCategory = categoryService.insertCategory(request);
            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/admin/updateCategory/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO request, @PathVariable Long id) {
        CategoryDTO savedCategory = categoryService.updateCategory(request, id);
        return ResponseEntity.ok(savedCategory);
    }

    @DeleteMapping("/admin/deleteCategory/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("deleted successfully!");
    }
}
