package com.base.springsecurity.service;

import com.base.springsecurity.exception.CartItemException;
import com.base.springsecurity.exception.UserException;
import com.base.springsecurity.model.entity.Cart;
import com.base.springsecurity.model.entity.CartItem;
import com.base.springsecurity.model.entity.Product;

public interface CartItemService {

     CartItem createCartItem(CartItem cartItem);

     CartItem updateCartItem(Long userId, Long id,CartItem cartItem)
             throws CartItemException, UserException;

     CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

     void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;

     CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
