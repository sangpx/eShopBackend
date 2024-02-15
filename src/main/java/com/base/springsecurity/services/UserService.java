package com.base.springsecurity.services;

import com.base.springsecurity.exceptions.ProductException;
import com.base.springsecurity.exceptions.UserException;
import com.base.springsecurity.models.entity.User;

import java.util.Optional;

public interface UserService {
    //Kiem tra UserName da ton tai chua
    boolean existsByUsername(String userName);
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String userName);
    Optional<User> findById(Long userId) throws UserException;
    User saveOrUpdate(User user);
}
