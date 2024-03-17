package com.base.springsecurity.controllers;

import com.base.springsecurity.exceptions.OrderException;
import com.base.springsecurity.exceptions.UserException;
import com.base.springsecurity.models.entity.Address;
import com.base.springsecurity.models.entity.Order;
import com.base.springsecurity.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrderHandler(@RequestParam Long userId,
        @RequestBody Address shippingAddress)
        throws UserException, OrderException {
            Order order = orderService.createOrder(userId, shippingAddress);
            return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @GetMapping("/usersOrderHistory")
    public ResponseEntity<List<Order>> usersOrderHistoryHandler(@RequestParam Long userId)
        throws UserException, OrderException{
            List<Order> orders = orderService.usersOrderHistory(userId);
            return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/findOrderHandler/{orderId}")
    public ResponseEntity< Order> findOrderHandler(@PathVariable Long orderId)
        throws OrderException, UserException{
            Order orders = orderService.findOrderById(orderId);
            return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
