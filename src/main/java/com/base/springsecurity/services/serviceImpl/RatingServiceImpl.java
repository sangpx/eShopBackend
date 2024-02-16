package com.base.springsecurity.services.serviceImpl;

import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.models.dto.catalog.rating.RatingDTO;
import com.base.springsecurity.models.entity.Product;
import com.base.springsecurity.models.entity.Rating;
import com.base.springsecurity.models.entity.User;
import com.base.springsecurity.repository.RatingRepository;
import com.base.springsecurity.services.ProductService;
import com.base.springsecurity.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Rating createRating(RatingDTO ratingDTO, User user) throws ProductException {
        Product product = productService.getDetailProduct(ratingDTO.getProductId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setRating(ratingDTO.getRating());
        rating.setUser(user);
        rating.setCreatedAt(new Date());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
