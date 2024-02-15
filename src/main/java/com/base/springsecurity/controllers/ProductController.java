package com.base.springsecurity.controllers;


import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.models.dto.payload.response.MessageResponse;
import com.base.springsecurity.models.dto.payload.response.PageResult;
import com.base.springsecurity.models.entity.Product;
import com.base.springsecurity.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProductPaging")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public PageResult<Page<Product>> getProductPaging(
            @RequestParam int offSet, @RequestParam int pageSize, @RequestParam String filed) throws ProductException{
        Page<Product> productPages = productService.getProductsWithPaginationAndSorting(offSet, pageSize, filed);
        return new PageResult<>(productPages.getSize(), productPages);
    }

    @GetMapping("/getDetailProduct")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Product> getDetailProduct(
            @RequestParam Long productId) throws ProductException{
        Product product = productService.getDetailProduct(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/admin/insertProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> insertProduct(@RequestParam MultipartFile file, @RequestParam String title, @RequestParam String description,
         @RequestParam int price, @RequestParam int discountedPrice, @RequestParam int discountPersent,
         @RequestParam  int quantity, @RequestParam Long categoryId, @RequestParam String brand, @RequestParam String color
        ) throws ProductException {
            productService.insertProduct(file, title, description,price, discountedPrice, discountPersent, quantity, categoryId, brand, color);
            return new ResponseEntity<>("Insert Successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/admin/updateProduct/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@RequestParam MultipartFile file, @RequestParam String title, @RequestParam String description,
       @RequestParam int price, @RequestParam int discountedPrice, @RequestParam int discountPersent,
       @RequestParam  int quantity, @RequestParam Long categoryId, @RequestParam String brand, @RequestParam String color,
           @PathVariable Long productId
    ) throws ProductException {
        productService.updateProduct(file, title, description,price, discountedPrice, discountPersent, quantity, categoryId, brand, color, productId);
        return ResponseEntity.ok("Update Successfully!");
    }

    @DeleteMapping("/admin/deleteProduct/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) throws ProductException {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Delete product Successfully!");
    }
}
