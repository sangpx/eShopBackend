package com.base.springsecurity.controller;


import com.base.springsecurity.exception.CartItemException;
import com.base.springsecurity.exception.ProductException;
import com.base.springsecurity.exception.UserException;
import com.base.springsecurity.model.dto.catalog.cart.CartDTO;
import com.base.springsecurity.model.dto.payload.response.MessageResponse;
import com.base.springsecurity.model.entity.Cart;
import com.base.springsecurity.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/getAllCartItemByUser")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Cart> getAllCartItemByUser(@RequestParam Long userId) throws UserException {
            Cart cart = cartService.findUserCart(userId);
            return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    //Add Item to Cart
    @PutMapping("/addItemToCart")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> addItemToCart(@RequestParam Long userId, @RequestBody CartDTO cartDTO) throws ProductException, CartItemException, UserException {
       try {
            cartService.addCartItem(userId, cartDTO);
            return ResponseEntity.ok(new MessageResponse("Add To Cart Success!", true));
       } catch (ProductException e) {
           return ResponseEntity.badRequest().body(new MessageResponse("Failed to add item to cart: " + e.getMessage(), false));
       }
    }
}
