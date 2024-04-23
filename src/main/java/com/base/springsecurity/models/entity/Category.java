package com.base.springsecurity.models.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 5, message = "Category name must contain at least 5 characters")
    private String name;
    private int level;

    @ManyToOne()
    @JoinColumn(name = "parent_category_id")
    @JsonBackReference
    private Category parentCategory;

    //Quan he mot - nhieu
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> listProducts;
}
