package com.base.springsecurity.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends AbstractBaseEntity<Long> {

    @NotBlank
    @Size(min = 5, message = "Category name must contain at least 5 characters")
    private String name;
    private int level;

    //Quan he mot - nhieu
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> listProducts;
}
