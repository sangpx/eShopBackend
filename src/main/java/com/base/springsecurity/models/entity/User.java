package com.base.springsecurity.models.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email")
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
  @ToString.Exclude // Ko sử dụng trong toString()
  private Set<Address> addresses = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
  @ToString.Exclude // Ko sử dụng trong toString()
  private Set<Rating> ratings = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
  @ToString.Exclude // Ko sử dụng trong toString()
  private Set<Review> reviews = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<Order> orders = new HashSet<>();

  //Xet moi quan he nhieu - nhieu
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_paymentInfomation",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "paymentInfomation_id"))
  private Set<PaymentInformation> listPaymentInformations = new HashSet<>();


}
