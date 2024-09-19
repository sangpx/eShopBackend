package com.base.springsecurity.service;

import com.base.springsecurity.exception.ProductException;
import com.base.springsecurity.model.dto.catalog.review.ReviewDTO;
import com.base.springsecurity.model.entity.Review;
import com.base.springsecurity.model.entity.User;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewDTO reviewDTO, User user) throws ProductException;
    List<Review> getProductsReview(Long productId);
}
