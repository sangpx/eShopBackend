package com.base.springsecurity.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @NotBlank
    @Size(min = 5, message = "Street name must contain at least 5 characters")
    private String street;
    @NotBlank
    @Size(min = 5, message = "Building name must contain at least 5 characters")
    private String buildingName;
    @NotBlank
    @Size(min = 4, message = "City name must contain at least 4 characters")
    private String city;

    @ManyToMany(mappedBy = "addresses")
    private List<User> users =new ArrayList<>();

}
