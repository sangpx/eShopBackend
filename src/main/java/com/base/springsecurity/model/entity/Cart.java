package com.base.springsecurity.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
public class Cart extends AbstractBaseEntity<Long> {


    @Column(name = "total_price")
    private double totalPrice;

    @Column(name="total_item")
    private int totalItem;

    @Column(name="total_discounted_price")
    private double totalDiscountedPrice;

    @Column(name="discounte")
    private long discounte;

    @OneToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "cart")
//    @JsonIgnore
    private List<CartItem> cartItems;
}
