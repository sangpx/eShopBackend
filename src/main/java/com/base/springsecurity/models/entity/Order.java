package com.base.springsecurity.models.entity;

import com.base.springsecurity.models.entity.domain.OrderStatus;
import com.fasterxml.jackson.annotation.*;
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

    @Column(name="order_date")
    private Date orderDate;

    @Column(name="delivery_date")
    private Date deliveryDate;

    @Column(name="total_price")
    private double totalPrice;

    @Column(name="total_discounted_price")
    private double totalDiscountedPrice;

    @Column(name="discounte")
    private double discounte;

    @Column(name="order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name="total_item")
    private double totalItem;

    @Column(name="created_at")
    private Date createdAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "address_id" , insertable=false, updatable=false)
    private Address shippingAddress;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id" , insertable=false, updatable=false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<OrderItem> orderItems = new HashSet<>();

    @Embedded
    private PaymentDetails paymentDetails = new PaymentDetails();
}
