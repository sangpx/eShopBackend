package com.base.springsecurity.controller;

import com.base.springsecurity.exception.OrderException;
import com.base.springsecurity.exception.UserException;
import com.base.springsecurity.model.entity.Address;
import com.base.springsecurity.model.entity.Order;
import com.base.springsecurity.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/createOrder")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Order> createOrderHandler(@RequestBody Address shippingAddress)
        throws UserException, OrderException {
            Order order = orderService.createOrder(shippingAddress);
            return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @GetMapping("/usersOrderHistory")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Order>> usersOrderHistoryHandler(@RequestParam Long userId)
        throws UserException, OrderException{
            List<Order> orders = orderService.usersOrderHistory(userId);
            return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/findOrderHandler/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity< Order> findOrderHandler(@PathVariable Long orderId)
        throws OrderException, UserException{
            Order orders = orderService.findOrderById(orderId);
            return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
