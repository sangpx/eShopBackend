package com.base.springsecurity.service;

import com.base.springsecurity.exception.CartItemException;
import com.base.springsecurity.exception.ProductException;
import com.base.springsecurity.exception.UserException;
import com.base.springsecurity.model.dto.catalog.cart.CartDTO;
import com.base.springsecurity.model.entity.Cart;
import com.base.springsecurity.model.entity.User;

public interface CartService {

     Cart createCart(User user);
     String addCartItem(Long userId, CartDTO cartDTO) throws ProductException, CartItemException, UserException;
     Cart findUserCart(Long userId);
}
