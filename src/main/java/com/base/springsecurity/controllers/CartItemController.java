package com.base.springsecurity.controllers;

import com.base.springsecurity.exceptions.CartItemException;
import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.exceptions.UserException;
import com.base.springsecurity.models.dto.catalog.cart.CartDTO;
import com.base.springsecurity.models.dto.payload.response.MessageResponse;
import com.base.springsecurity.models.entity.Cart;
import com.base.springsecurity.models.entity.CartItem;
import com.base.springsecurity.services.CartItemService;
import com.base.springsecurity.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cartitems")
public class CartItemController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    //Delete Item to Cart
    @DeleteMapping("/deleteItemToCart")
    public ResponseEntity<MessageResponse> deleteCartItemHandler(@RequestParam Long userId,
         @RequestParam Long cartItemId)  throws UserException, CartItemException {
        cartItemService.removeCartItem(userId, cartItemId);
        return ResponseEntity.ok(new MessageResponse("Delete Success!", true));
    }

    //Update Item to Cart
    @PutMapping("/updateItemToCart/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(@RequestParam Long userId,
              @PathVariable Long cartItemId, @RequestBody CartItem cartItem)
            throws UserException, CartItemException {
        try {
            CartItem updateCartItem = cartItemService.updateCartItem(userId, cartItemId, cartItem);
            return new ResponseEntity<>(updateCartItem, HttpStatus.OK);
        } catch (CartItemException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
