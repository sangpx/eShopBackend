package com.base.springsecurity.services;

import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.models.dto.catalog.rating.RatingDTO;
import com.base.springsecurity.models.entity.Rating;
import com.base.springsecurity.models.entity.User;

import java.util.List;

public interface RatingService {
    Rating createRating(RatingDTO ratingDTO, User user) throws ProductException;
    List<Rating> getProductsRating(Long productId);
}
