package com.base.springsecurity.service.serviceImpl;

import com.base.springsecurity.exception.OrderException;
import com.base.springsecurity.exception.UserException;
import com.base.springsecurity.model.entity.*;
import com.base.springsecurity.model.entity.domain.OrderStatus;
import com.base.springsecurity.repository.AddressRepository;
import com.base.springsecurity.repository.OrderItemRepository;
import com.base.springsecurity.repository.OrderRepository;
import com.base.springsecurity.repository.UserRepository;
import com.base.springsecurity.service.CartService;
import com.base.springsecurity.service.OrderItemService;
import com.base.springsecurity.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(Address shippingAdress)
            throws UserException, OrderException {

        // Lấy thông tin về User từ token JWT
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        // Lấy thông tin người dùng từ cơ sở dữ liệu
        User currentUser = userRepository.findByUsername(currentUserName)
                .orElseThrow(()
                        -> new UsernameNotFoundException("Không tìm thấy thông tin người dùng!"));


        // Cập nhật địa chỉ giao hàng và lưu trữ vào cơ sở dữ liệu
        shippingAdress.setUser(currentUser);
        Address address = addressRepository.save(shippingAdress);
        currentUser.getAddresses().add(address);
        userRepository.save(currentUser);

        // Lấy giỏ hàng của người dùng
        Cart cart = cartService.findUserCart(currentUser.getId());
        List<OrderItem> listOrderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setProduct(cartItem.getProduct());
            // Lấy ngày hiện tại
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            // Thêm ba ngày vào ngày hiện tại
            calendar.add(Calendar.DAY_OF_MONTH, 3);
            // Lấy ngày vận chuyển sau khi thêm ba ngày
            Date deliveryDate = calendar.getTime();
            // Đặt ngày vận chuyển cho orderItem
            orderItem.setDeliveryDate(deliveryDate);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSize(cartItem.getSize());
            orderItem.setDiscountedPrice(cartItem.getDiscountedPrice());
            OrderItem createdOrderItem = orderItemRepository.save(orderItem);
            listOrderItems.add(createdOrderItem);
        }

        // Tính toán tổng số lượng và giá trị của đơn hàng
        double totalPrice = listOrderItems
                .stream()
                .mapToDouble(OrderItem::getPrice).sum();
        double totalDiscountedPrice = listOrderItems
                .stream()
                .mapToDouble(OrderItem::getDiscountedPrice).sum();

        // Tạo và lưu trữ đơn hàng
        Order createdOrder = new Order();
        createdOrder.setUser(currentUser);
        createdOrder.setOrderItems(listOrderItems);
        createdOrder.setTotalPrice(totalPrice);
        createdOrder.setTotalDiscountedPrice(totalDiscountedPrice);
        createdOrder.setDiscounte(cart.getDiscounte());
        createdOrder.setTotalItem(cart.getTotalItem());
        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // Thêm ba ngày vào ngày hiện tại
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        // Lấy ngày vận chuyển sau khi thêm ba ngày
        Date deliveryDate = calendar.getTime();
        // Đặt ngày vận chuyển cho orderItem
        createdOrder.setDeliveryDate(deliveryDate);
        createdOrder.setShippingAddress(address);
        createdOrder.setOrderDate(new Date());
        createdOrder.setOrderStatus(OrderStatus.PENDING);
        createdOrder.setCreatedAt(new Date());

        Order savedOrder=orderRepository.save(createdOrder);

        for(OrderItem item : listOrderItems) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }
        return savedOrder;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if(orderOpt.isPresent()) {
            return orderOpt.get();
        }
        throw new OrderException("Order not exist with id: " + orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        List<Order> listOrders = orderRepository.getUserOrders(userId);
        return listOrders;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus(OrderStatus.PLACED);
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus(OrderStatus.SHIPPED);
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus(OrderStatus.DELIVERED);
        return orderRepository.save(order);
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }
}
