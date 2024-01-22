package com.base.springsecurity.models.entity;

import com.base.springsecurity.models.entity.domain.ProductSize;
import jakarta.persistence.*;
import lombok.*;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductSize name;

    private int quantity;
}
