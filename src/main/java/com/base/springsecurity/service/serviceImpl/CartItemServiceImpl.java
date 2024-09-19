package com.base.springsecurity.service.serviceImpl;

import com.base.springsecurity.exception.CartItemException;
import com.base.springsecurity.exception.UserException;
import com.base.springsecurity.model.entity.Cart;
import com.base.springsecurity.model.entity.CartItem;
import com.base.springsecurity.model.entity.Product;
import com.base.springsecurity.model.entity.User;
import com.base.springsecurity.repository.CartItemRepository;
import com.base.springsecurity.repository.CartRepository;
import com.base.springsecurity.repository.UserRepository;
import com.base.springsecurity.service.CartItemService;
import com.base.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());
        CartItem createCartItem = cartItemRepository.save(cartItem);
        return createCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        // Lấy thông tin người dùng hiện tại từ đối tượng Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> OptionalUser = userRepository.findByUsername(userDetails.getUsername());
        User currentUser = OptionalUser.get();

        // Kiểm tra xem userId có trùng với người dùng hiện tại không
        if (!currentUser.getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to update items to another user's cart");
        }

        CartItem item = findCartItemById(id);
        // Cập nhật thuộc tính của item với giá trị mới từ cartItem truyền vào
        item.setQuantity(cartItem.getQuantity());
        item.setPrice(item.getQuantity() * item.getProduct().getPrice());
        item.setDiscountedPrice(item.getQuantity() * item.getProduct().getDiscountedPrice());

        // Lưu thay đổi vào cơ sở dữ liệu và trả về đối tượng đã được cập nhật
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        // Lấy thông tin người dùng hiện tại từ đối tượng Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> OptionalUser = userRepository.findByUsername(userDetails.getUsername());
        User currentUser = OptionalUser.get();
        // Kiểm tra xem userId có trùng với người dùng hiện tại không
        if (!currentUser.getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to see items to another user's cart");
        }
        CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        // Lấy thông tin người dùng hiện tại từ đối tượng Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> OptionalUser = userRepository.findByUsername(userDetails.getUsername());
        User currentUser = OptionalUser.get();

        // Kiểm tra xem userId có trùng với người dùng hiện tại không
        if (!currentUser.getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to remove items to another user's cart");
        }
        CartItem cartItem = findCartItemById(cartItemId);
        if(currentUser.getId().equals(cartItem.getUserId())) {
            cartItemRepository.deleteById(cartItemId);
        } else  {
            throw new CartItemException("Cart item does not belong to the specified user.");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
       Optional<CartItem> optCartItem = cartItemRepository.findById(cartItemId);
        // Kiểm tra xem cartItem có tồn tại hay không
       if(optCartItem.isPresent()) {
           return optCartItem.get();
       }
        throw new CartItemException("cartItem not found with id : "+ cartItemId);
    }
}
