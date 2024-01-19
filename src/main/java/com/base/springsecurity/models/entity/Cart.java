package com.base.springsecurity.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "carts")
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    //Quan he 1 - 1
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    //Quan he mot - nhieu
    @OneToMany(mappedBy = "cart", cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    private Double totalPrice = 0.0;
}
