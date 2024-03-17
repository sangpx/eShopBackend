package com.base.springsecurity.services.serviceImpl;

import com.base.springsecurity.models.entity.OrderItem;
import com.base.springsecurity.repository.OrderItemRepository;
import com.base.springsecurity.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
