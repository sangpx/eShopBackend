package com.base.springsecurity.model.entity;

import com.base.springsecurity.model.entity.domain.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role extends AbstractBaseEntity<Integer> {

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

}