package com.base.springsecurity.models.entity;

import com.base.springsecurity.models.entity.domain.ERole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  public Role() {

  }

  public Role(ERole name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ERole getName() {
    return name;
  }

  public void setName(ERole name) {
    this.name = name;
  }
}