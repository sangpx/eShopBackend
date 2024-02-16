package com.base.springsecurity.services.serviceImpl;

import com.base.springsecurity.exceptions.CartItemException;
import com.base.springsecurity.exceptions.UserException;
import com.base.springsecurity.models.entity.Cart;
import com.base.springsecurity.models.entity.CartItem;
import com.base.springsecurity.models.entity.Product;
import com.base.springsecurity.models.entity.User;
import com.base.springsecurity.repository.CartItemRepository;
import com.base.springsecurity.repository.CartRepository;
import com.base.springsecurity.services.CartItemService;
import com.base.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        CartItem item = findCartItemById(id);
        Optional<User> userOptional = userService.findById(userId);

        if (!userOptional.isPresent()) {
            throw new UserException("User not found with id: " + userId);
        }

        User user = userOptional.get();

        if (!user.getId().equals(item.getUserId())) {
            throw new CartItemException("You can't update another user's cart item.");
        }

        // Cập nhật thuộc tính của item với giá trị mới từ cartItem truyền vào
        item.setQuantity(cartItem.getQuantity());
        item.setPrice(item.getQuantity() * item.getProduct().getPrice());
        item.setDiscountedPrice(item.getQuantity() * item.getProduct().getDiscountedPrice());

        // Lưu thay đổi vào cơ sở dữ liệu và trả về đối tượng đã được cập nhật
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        Optional<User> userOtp = userService.findById(userId);
        if(userOtp.isPresent()) {
           User user = userOtp.get();
            if(user.getId().equals(cartItem.getUserId())) {
                cartItemRepository.deleteById(cartItemId);
            } else  {
                throw new CartItemException("Cart item does not belong to the specified user.");
            }
        }
        else {
            throw new UserException("You can't remove another users item!");
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
