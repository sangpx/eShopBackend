package com.base.springsecurity.service.serviceImpl;

import com.base.springsecurity.exception.ProductException;
import com.base.springsecurity.model.dto.catalog.rating.RatingDTO;
import com.base.springsecurity.model.entity.Product;
import com.base.springsecurity.model.entity.Rating;
import com.base.springsecurity.model.entity.User;
import com.base.springsecurity.repository.RatingRepository;
import com.base.springsecurity.service.ProductService;
import com.base.springsecurity.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final ProductService productService;

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
