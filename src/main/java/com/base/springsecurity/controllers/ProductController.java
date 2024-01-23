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
    @PreAuthorize("hasRole('ADMIN')")
    public PageResult<Page<Product>> getProductPaging(
            @RequestParam int offSet, @RequestParam int pageSize, @RequestParam String filed){
        Page<Product> productPages = productService.findProductsWithPaginationAndSorting(offSet, pageSize, filed);
        return new PageResult<>(productPages.getSize(), productPages);
    }

    @PostMapping("/admin/insertProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> insertProduct(@RequestParam MultipartFile file, @RequestParam String title, @RequestParam String description,
                                                         @RequestParam int price, @RequestParam int discountedPrice, @RequestParam int discountPersent,
                                                         @RequestParam  int quantity, @RequestParam Long categoryId, @RequestParam String brand, @RequestParam String color,
                                                         @RequestParam int numRatings) throws ProductException {
            boolean isSuccess = productService.insertProduct(file, title, description,price, discountedPrice, discountPersent, quantity, categoryId, brand, color, numRatings);
            return new ResponseEntity<>("Insert Successfully!", HttpStatus.CREATED);
    }
}
