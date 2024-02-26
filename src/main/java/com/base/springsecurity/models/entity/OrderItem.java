package com.base.springsecurity.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String size;

    private int quantity;

    private double price;

    @Column(name = "discounted_price")
    private double discountedPrice;

    private Long userId;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id" , insertable=false, updatable=false)
    private Order order;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id" , insertable=false, updatable=false)
    private Product product;
}
