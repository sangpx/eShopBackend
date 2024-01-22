package com.base.springsecurity.models.entity;

import com.base.springsecurity.models.entity.domain.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_id")
    private String orderId;

    @Column(name="order_date")
    private Date orderDate;

    @Column(name="delivery_date")
    private Date deliveryDate;

    @Column(name="total_price")
    private double totalPrice;

    @Column(name="total_discounted_price")
    private Integer totalDiscountedPrice;

    @Column(name="discounte")
    private Integer discounte;

    @Column(name="order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name="total_item")
    private int totalItem;

    @Column(name="created_at")
    private Date createdAt;

    @ManyToOne
    @JsonIgnore
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "address_id" , insertable=false, updatable=false)
    private Address shippingAddress;

    @ManyToOne
    @JsonIgnore
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "user_id" , insertable=false, updatable=false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Ko sử dụng trong toString()
    private Set<OrderItem> orderItems = new HashSet<>();

    @Embedded
    private PaymentDetails paymentDetails = new PaymentDetails();


}
