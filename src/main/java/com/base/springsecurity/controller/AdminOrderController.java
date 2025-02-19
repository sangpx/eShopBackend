package com.base.springsecurity.controller;
import com.base.springsecurity.exception.OrderException;
import com.base.springsecurity.model.dto.payload.response.MessageResponse;
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
@RequestMapping("/api/admin/orders")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminOrderController {
    
    private final OrderService orderService;

    @GetMapping("/getAllOrdersHandler")
    public ResponseEntity<List<Order>> getAllOrdersHandler(){
        List<Order> orders=orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> ConfirmedOrderHandler(@PathVariable Long orderId) throws OrderException {
        Order order = orderService.confirmedOrder(orderId);
        return new ResponseEntity<Order>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> shippedOrderHandler(@PathVariable Long orderId) throws OrderException{
        Order order = orderService.shippedOrder(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> deliveredOrderHandler(@PathVariable Long orderId) throws OrderException{
        Order order = orderService.deliveredOrder(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> canceledOrderHandler(@PathVariable Long orderId) throws OrderException{
        Order order = orderService.cancledOrder(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/placed")
    public ResponseEntity<Order> placedOrderHandler(@PathVariable Long orderId) throws OrderException{
        Order order = orderService.placedOrder(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<MessageResponse> deleteOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException{
        orderService.deleteOrder(orderId);
        MessageResponse res = new MessageResponse("Order Deleted Successfully",true);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

}
