package com.base.springsecurity.service;

import com.base.springsecurity.exception.OrderException;
import com.base.springsecurity.exception.UserException;
import com.base.springsecurity.model.entity.Address;
import com.base.springsecurity.model.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Address shippingAdress) throws UserException, OrderException;

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
