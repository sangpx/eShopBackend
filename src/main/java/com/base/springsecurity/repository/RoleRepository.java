package com.base.springsecurity.repository;

import java.util.Optional;

import com.base.springsecurity.models.entity.domain.ERole;
import com.base.springsecurity.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(ERole name);
}
