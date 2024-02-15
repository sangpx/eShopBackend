package com.base.springsecurity.repository;

import com.base.springsecurity.models.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}
