package com.base.springsecurity.services.serviceImpl;

import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.models.entity.Category;
import com.base.springsecurity.models.entity.Product;
import com.base.springsecurity.repository.CategoryRepository;
import com.base.springsecurity.repository.ProductRepository;
import com.base.springsecurity.services.FilesStorageService;
import com.base.springsecurity.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FilesStorageService storageService;

    //Insert Product
    @Override
    public boolean insertProduct(MultipartFile file,
         String title, String description, int price, int discountedPrice, int discountPersent,
         int quantity, Long categoryId, String brand, String color, int numRatings)
            throws ProductException {
        boolean isInsertSuccess = false;
       try {
            Boolean isSavedFileSuccess = storageService.save(file);
            if(isSavedFileSuccess) {
                //setup du lieu
                Product product = new Product();
                product.setImage(file.getOriginalFilename());
                product.setTitle(title);
                product.setDescription(description);
                product.setPrice(price);
                product.setDiscountedPrice(discountedPrice);
                product.setDiscountPersent(discountPersent);
                product.setQuantity(quantity);
                product.setBrand(brand);
                product.setColor(color);
                product.setNumRatings(numRatings);
                Category category = new Category();
                category.setId(categoryId);
                product.setCategory(category);
                //tien hanh save Product
                productRepository.save(product);
                isInsertSuccess = true;
            }
       } catch (Exception e) {
           throw new ProductException("Cannot insert product! ");
       }
           return isInsertSuccess;
    }

    @Override
    public Page<Product> findProductsWithPaginationAndSorting(int offSet, int pageSize, String filed) {
        Page<Product> productPage = productRepository
                .findAll(PageRequest.of(offSet, pageSize).withSort(Sort.by(filed)));
        return productPage;
    }
}
