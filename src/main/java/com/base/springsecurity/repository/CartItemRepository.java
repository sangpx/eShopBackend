package com.base.springsecurity.repository;

import com.base.springsecurity.models.entity.Cart;
import com.base.springsecurity.models.entity.CartItem;
import com.base.springsecurity.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("select ci from CartItem ci " +
            "where ci.cart=:cart and ci.product=:product " +
            "and ci.size=:size and ci.userId=:userId")
    CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product") Product product,
                             @Param("size") String size, @Param("userId") Long userId);
}
