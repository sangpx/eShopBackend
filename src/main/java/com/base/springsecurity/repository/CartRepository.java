package com.base.springsecurity.repository;

import com.base.springsecurity.models.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c where c.user.id=:userId")
    Cart findByUserId(@Param("userId") Long userId);
}
