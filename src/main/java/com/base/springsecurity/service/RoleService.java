package com.base.springsecurity.service;

import com.base.springsecurity.model.entity.domain.ERole;
import com.base.springsecurity.model.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();
    Optional<Role> findByRoleName(ERole roleName);
}
