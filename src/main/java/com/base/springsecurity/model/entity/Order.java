package com.base.springsecurity.model.entity;

import com.base.springsecurity.model.entity.domain.OrderStatus;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_date")
    private Date orderDate;

    @Column(name="delivery_date")
    private Date deliveryDate;

    @Column(name="total_price")
    private double totalPrice;

    @Column(name="total_discounted_price")
    private double totalDiscountedPrice;

    @Column(name="discounte")
    private long discounte;

    @Column(name="order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name="total_item")
    private double totalItem;

    @Column(name="created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    @JsonIgnore
    private Address shippingAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<OrderItem> orderItems;
}
