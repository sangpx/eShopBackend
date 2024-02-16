package com.base.springsecurity.services;

import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.models.dto.catalog.review.ReviewDTO;
import com.base.springsecurity.models.entity.Review;
import com.base.springsecurity.models.entity.User;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewDTO reviewDTO, User user) throws ProductException;
    List<Review> getProductsReview(Long productId);
}
