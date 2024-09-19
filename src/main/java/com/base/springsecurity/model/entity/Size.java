package com.base.springsecurity.model.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Size {

    private String name;

    private int quantity;
}
