package com.base.springsecurity.models.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Size {

    private String name;

    private int quantity;
}
