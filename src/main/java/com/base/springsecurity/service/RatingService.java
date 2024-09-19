package com.base.springsecurity.service;

import com.base.springsecurity.exception.ProductException;
import com.base.springsecurity.model.dto.catalog.rating.RatingDTO;
import com.base.springsecurity.model.entity.Rating;
import com.base.springsecurity.model.entity.User;

import java.util.List;

public interface RatingService {
    Rating createRating(RatingDTO ratingDTO, User user) throws ProductException;
    List<Rating> getProductsRating(Long productId);
}
