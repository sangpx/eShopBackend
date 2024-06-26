package com.base.springsecurity.models.dto.catalog.cart;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long productId;
    private String size;
    private int quantity;
    private Integer price;
}
