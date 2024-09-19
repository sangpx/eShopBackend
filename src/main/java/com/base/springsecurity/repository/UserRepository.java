package com.base.springsecurity.repository;

import java.util.Optional;

import com.base.springsecurity.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  Optional<User> findById(Long userId);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
}
