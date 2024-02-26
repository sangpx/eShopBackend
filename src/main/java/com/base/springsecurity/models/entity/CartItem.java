package com.base.springsecurity.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String size;

    private int quantity;

    private double price;

    private double discountedPrice;

    @JsonIgnore
    @ManyToOne
    private Cart cart;

    @JsonIgnore
    @ManyToOne
    private Product product;
}
