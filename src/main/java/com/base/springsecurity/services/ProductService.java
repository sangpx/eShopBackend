package com.base.springsecurity.services;

import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.models.dto.catalog.product.ProductDTO;
import com.base.springsecurity.models.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface ProductService {
    //For Admin
    boolean insertProduct(MultipartFile file, String title, String description,
                          int price, int discountedPrice, int discountPersent,
                          int quantity, Long categoryId, String brand, String color) throws ProductException;
    boolean updateProduct(MultipartFile file, String title, String description,
                          int price, int discountedPrice, int discountPersent,
                          int quantity, Long categoryId, String brand, String color, Long productId) throws ProductException;
    public void deleteProduct(Long productId) throws ProductException;


    //For Admin and User
    Product getDetailProduct(Long productId) throws ProductException;
    Page<Product> findProductsWithPaginationAndSorting(int offSet, int pageSize, String filed) throws ProductException;
    Page<Product> findProductsByCategory(String categoryName) throws ProductException;
    Page<Product> searchProduct(String query) throws ProductException;

}
