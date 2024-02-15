package com.base.springsecurity.services;

import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.models.dto.catalog.cart.CartDTO;
import com.base.springsecurity.models.entity.Cart;
import com.base.springsecurity.models.entity.User;

import java.util.List;

public interface CartService {

     Cart createCart(User user);
     String addCartItem(Long userId, CartDTO cartDTO) throws ProductException;
     Cart findUserCart(Long userId);
}
