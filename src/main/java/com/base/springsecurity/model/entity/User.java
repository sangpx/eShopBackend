package com.base.springsecurity.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email")
    })
public class User extends AbstractBaseEntity<Long> {


  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  //Xet moi quan he nhieu - nhieu
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "user")
//  @JsonManagedReference
  @JsonIgnore
  private List<Address> addresses;

  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  private List<Rating> ratings;

  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  private List<Review> reviews;

  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  private List<Order> orders;
}
