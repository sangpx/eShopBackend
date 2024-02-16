package com.base.springsecurity.services.serviceImpl;

import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.models.dto.catalog.review.ReviewDTO;
import com.base.springsecurity.models.entity.Product;
import com.base.springsecurity.models.entity.Rating;
import com.base.springsecurity.models.entity.Review;
import com.base.springsecurity.models.entity.User;
import com.base.springsecurity.repository.ReviewRepository;
import com.base.springsecurity.services.ProductService;
import com.base.springsecurity.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Review createReview(ReviewDTO reviewDTO, User user) throws ProductException {
        Product product = productService.getDetailProduct(reviewDTO.getProductId());
        Review review = new Review();
        review.setProduct(product);
        review.setReview(reviewDTO.getReview());
        review.setUser(user);
        review.setCreatedAt(new Date());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getProductsReview(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
