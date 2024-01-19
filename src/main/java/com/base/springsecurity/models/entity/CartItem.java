package com.base.springsecurity.models.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "cart_items")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    //Quan he nhieu - mot
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    //Quan he nhieu - mot
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    private double discount;
    private double productPrice;
}
