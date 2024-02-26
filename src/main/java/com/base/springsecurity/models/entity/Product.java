package com.base.springsecurity.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
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

    @ElementCollection
    @Column(name = "sizes")
    private Set<Size> sizes = new HashSet<>();

    @ManyToOne(optional=false)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;

    @OneToMany(mappedBy = "product", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonManagedReference
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Ko sử dụng trong toString()
    private Set<Rating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonManagedReference
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Ko sử dụng trong toString()
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonManagedReference
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Ko sử dụng trong toString()
    private Set<OrderItem> orderItems = new HashSet<>();
}
