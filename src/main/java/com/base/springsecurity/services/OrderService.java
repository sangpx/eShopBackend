package com.base.springsecurity.services;

import com.base.springsecurity.exceptions.OrderException;
import com.base.springsecurity.exceptions.UserException;
import com.base.springsecurity.models.entity.Address;
import com.base.springsecurity.models.entity.Order;
import com.base.springsecurity.models.entity.User;

import java.util.List;

public interface OrderService {
     Order createOrder(Long userId, Address shippingAdress) throws UserException, OrderException;

     Order findOrderById(Long orderId) throws OrderException;

     List<Order> usersOrderHistory(Long userId);

     Order placedOrder(Long orderId) throws OrderException;

     Order confirmedOrder(Long orderId)throws OrderException;

     Order shippedOrder(Long orderId) throws OrderException;

     Order deliveredOrder(Long orderId) throws OrderException;

     Order cancledOrder(Long orderId) throws OrderException;

     List<Order> getAllOrders();

     void deleteOrder(Long orderId) throws OrderException;

}
