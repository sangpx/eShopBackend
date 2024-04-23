package com.base.springsecurity.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "discounted_price")
    private double discountedPrice;

    @Column(name="discount_persent")
    private double discountPersent;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @ElementCollection
    @Column(name = "sizes")
    private Set<Size> sizes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, updatable = false)
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderItem> orderItems;

}
