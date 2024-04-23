package com.base.springsecurity.services;

import com.base.springsecurity.exceptions.CartItemException;
import com.base.springsecurity.exceptions.UserException;
import com.base.springsecurity.models.entity.Cart;
import com.base.springsecurity.models.entity.CartItem;
import com.base.springsecurity.models.entity.Product;

public interface CartItemService {

     CartItem createCartItem(CartItem cartItem);

     CartItem updateCartItem(Long userId, Long id,CartItem cartItem)
             throws CartItemException, UserException;

     CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

     void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;

     CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
