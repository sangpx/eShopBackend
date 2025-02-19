package com.base.springsecurity.repository;

import com.base.springsecurity.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.product.id=:productId")
    List<Review> getAllProductsReview(@Param("productId")Long productId);
}
