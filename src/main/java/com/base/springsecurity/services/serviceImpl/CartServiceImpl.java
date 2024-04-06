package com.base.springsecurity.services.serviceImpl;

import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.exceptions.UserException;
import com.base.springsecurity.models.dto.catalog.cart.CartDTO;
import com.base.springsecurity.models.entity.Cart;
import com.base.springsecurity.models.entity.CartItem;
import com.base.springsecurity.models.entity.Product;
import com.base.springsecurity.models.entity.User;
import com.base.springsecurity.repository.CartRepository;
import com.base.springsecurity.repository.UserRepository;
import com.base.springsecurity.services.CartItemService;
import com.base.springsecurity.services.CartService;
import com.base.springsecurity.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;

    @Override
    public Cart createCart(User user) {
        Cart createdCart = new Cart();
        createdCart.setUser(user);
        return cartRepository.save(createdCart);
    }

    @Override
    public String addCartItem(Long userId, CartDTO cartDTO) throws  ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        if(cart == null) {
            // Nếu giỏ hàng chưa tồn tại, tạo một giỏ hàng mới
            cart = new Cart();
            // Gán người dùng cho giỏ hàng
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            cart.setUser(user);
            // Lưu giỏ hàng vào cơ sở dữ liệu
            cart = cartRepository.save(cart);
        }
        Product product = productService.getDetailProduct(cartDTO.getProductId());
        CartItem isPresent = cartItemService.isCartItemExist(cart, product, cartDTO.getSize(), userId);
        if(isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartDTO.getQuantity());
            cartItem.setUserId(userId);

            double price = cartDTO.getQuantity() * product.getPrice();
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
        double totalPrice = 0;
        double totalDiscountedPrice = 0;
        double totalItem = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getPrice();
            totalDiscountedPrice += cartItem.getDiscountedPrice();
            totalItem += cartItem.getQuantity();
        }
//        cart.setTotalItem(totalItem);
//        cart.setTotalPrice(totalPrice);
//        cart.setTotalDiscountedPrice(totalPrice - totalDiscountedPrice);
        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(cart.getCartItems().size());
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setDiscounte(totalPrice-totalDiscountedPrice);
        cart.setTotalItem(totalItem);

        return cartRepository.save(cart);
    }
}
