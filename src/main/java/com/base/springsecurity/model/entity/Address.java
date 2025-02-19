package com.base.springsecurity.model.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address extends AbstractBaseEntity<Long> {


    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "mobile")
    private String mobile;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable = false)
//    @JsonBackReference
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "shippingAddress", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
    @JsonIgnore
    private List<Order> orders;
}
