package com.base.springsecurity.model.dto.catalog.product;

import com.base.springsecurity.model.entity.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {
    private String title;

    private String description;

    private int price;

    private int discountedPrice;

    private int discountPersent;

    private int quantity;

    private String brand;

    private String color;

    private String image;

    private double rating;

    private Set<Size> size=new HashSet<>();

    private Date createdAt;

}
