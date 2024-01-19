package com.base.springsecurity.services;

import com.base.springsecurity.models.entity.domain.ERole;
import com.base.springsecurity.models.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();
    Optional<Role> findByRoleName(ERole roleName);
}
