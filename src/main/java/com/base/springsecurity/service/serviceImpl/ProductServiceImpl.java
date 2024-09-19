package com.base.springsecurity.service.serviceImpl;

import com.base.springsecurity.exception.ProductException;
import com.base.springsecurity.exception.ResourceNotFoundException;
import com.base.springsecurity.model.entity.Category;
import com.base.springsecurity.model.entity.Product;
import com.base.springsecurity.repository.ProductRepository;
import com.base.springsecurity.service.FilesStorageService;
import com.base.springsecurity.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FilesStorageService storageService;

    @Autowired
    private ModelMapper modelMapper;

    //Insert Product
    @Override
    public boolean insertProduct(MultipartFile file,
         String title, String description, int price, int discountedPrice, int discountPersent,
         int quantity, Long categoryId, String brand, String color)
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
                product.setCreatedAt(new Date());
                product.setUpdatedAt(new Date());
                Category category = new Category();
                category.setId(categoryId);
                product.setCategory(category);
                //tien hanh save Product
                productRepository.save(product);
                isInsertSuccess = true;
            }
       } catch (Exception e) {
           throw new ProductException("Cannot Insert Product! ");
       }
           return isInsertSuccess;
    }

    @Override
    public boolean updateProduct(MultipartFile file, String title, String description,
         int price, int discountedPrice, int discountPersent, int quantity,
         Long categoryId, String brand, String color, Long productId) throws ProductException {
        boolean isUpdateSuccess = false;
        try {
            Product productFromDB = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
            if (productFromDB == null) {
                throw new ProductException("Product not found with productId: " + productId);
            }
            Boolean isSavedFileSuccess = storageService.save(file);
            if(isSavedFileSuccess) {
                //setup du lieu
                Product product = new Product();
                product.setId(productId);
                product.setImage(file.getOriginalFilename());
                product.setTitle(title);
                product.setDescription(description);
                product.setPrice(price);
                product.setDiscountedPrice(discountedPrice);
                product.setDiscountPersent(discountPersent);
                product.setQuantity(quantity);
                product.setBrand(brand);
                product.setUpdatedAt(new Date());
                product.setColor(color);
                Category category = new Category();
                category.setId(categoryId);
                product.setCategory(category);
                //tien hanh save Product
                productRepository.save(product);
                isUpdateSuccess = true;
            }
        } catch (Exception e) {
            throw new ProductException("Cannot Update Product! ");
        }
        return isUpdateSuccess;
    }

    @Override
    public void deleteProduct(Long productId) throws ProductException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("Not found Product!"));
        productRepository.delete(product);
    }

    @Override
    public Product getDetailProduct(Long productId) throws ProductException {
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        if (productFromDB == null) {
            throw new ProductException("Product not found with productId: " + productId);
        }
        return productFromDB;
    }

    @Override
    public Page<Product> getProductsWithPaginationAndSorting(int offSet, int pageSize, String filed) throws ProductException{
        Page<Product> productPage = productRepository
                .findAll(PageRequest.of(offSet, pageSize).withSort(Sort.by(filed)));
        return productPage;
    }

    @Override
    public Page<Product> findProductsByCategory(String categoryName) throws ProductException {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category,
       List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice,
       Integer minDiscount, String sort, String stock, int offSet, int pageSize)
            throws ProductException {
        return null;
    }


}
