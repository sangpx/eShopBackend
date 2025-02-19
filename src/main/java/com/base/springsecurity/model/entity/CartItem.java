package com.base.springsecurity.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
public class CartItem extends AbstractBaseEntity<Long> {


    private Long userId;

    private String size;

    private int quantity;

    private double price;

    private double discountedPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
//    @JsonBackReference
    @JsonIgnore
    private Product product;
}
