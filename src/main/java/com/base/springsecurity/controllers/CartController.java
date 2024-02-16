package com.base.springsecurity.controllers;


import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.exceptions.UserException;
import com.base.springsecurity.models.dto.catalog.cart.CartDTO;
import com.base.springsecurity.models.dto.payload.response.MessageResponse;
import com.base.springsecurity.models.entity.Cart;
import com.base.springsecurity.models.entity.User;
import com.base.springsecurity.services.CartService;
import com.base.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/getAllCartItemByUser")
    public ResponseEntity<Cart> findUserCartHandler(@RequestParam Long userId) throws UserException {
            Cart cart = cartService.findUserCart(userId);
            return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    //Add Item to Cart
    @PutMapping("/addItemToCart")
    public ResponseEntity<MessageResponse> addItemToCart(@RequestParam Long userId, @RequestBody CartDTO cartDTO) throws ProductException {
       try {
            cartService.addCartItem(userId, cartDTO);
            return ResponseEntity.ok(new MessageResponse("Add To Cart Success!", true));
       } catch (ProductException e) {
           return ResponseEntity.badRequest().body(new MessageResponse("Failed to add item to cart: " + e.getMessage(), false));
       }
    }
}
