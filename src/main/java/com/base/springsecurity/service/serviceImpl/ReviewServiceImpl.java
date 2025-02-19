package com.base.springsecurity.service.serviceImpl;

import com.base.springsecurity.exception.ProductException;
import com.base.springsecurity.model.dto.catalog.review.ReviewDTO;
import com.base.springsecurity.model.entity.Product;
import com.base.springsecurity.model.entity.Review;
import com.base.springsecurity.model.entity.User;
import com.base.springsecurity.repository.ReviewRepository;
import com.base.springsecurity.service.ProductService;
import com.base.springsecurity.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;

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
