package com.base.springsecurity.services.serviceImpl;

import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.models.dto.catalog.cart.CartDTO;
import com.base.springsecurity.models.entity.Cart;
import com.base.springsecurity.models.entity.CartItem;
import com.base.springsecurity.models.entity.Product;
import com.base.springsecurity.models.entity.User;
import com.base.springsecurity.repository.CartRepository;
import com.base.springsecurity.services.CartItemService;
import com.base.springsecurity.services.CartService;
import com.base.springsecurity.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;

    @Override
    public Cart createCart(User user) {
        Cart createCart = new Cart();
        createCart.setUser(user);
        return cartRepository.save(createCart);
    }

    @Override
    public String addCartItem(Long userId, CartDTO cartDTO) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.getDetailProduct(cartDTO.getProductId());
        CartItem isPresent = cartItemService.isCartItemExist(cart, product, cartDTO.getSize(), userId);
        if(isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartDTO.getQuantity());
            cartItem.setUserId(userId);

            int price = cartDTO.getQuantity() * product.getPrice();
            cartItem.setPrice(price);
            cartItem.setSize(cartDTO.getSize());

            CartItem createCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createCartItem);
        }
        return "Item add to cart!";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getPrice();
            totalDiscountedPrice += cartItem.getDiscountedPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscountedPrice(totalPrice - totalDiscountedPrice);

        return cartRepository.save(cart);
    }
}
