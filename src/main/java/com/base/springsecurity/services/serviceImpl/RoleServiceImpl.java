package com.base.springsecurity.services.serviceImpl;

import com.base.springsecurity.models.entity.domain.ERole;
import com.base.springsecurity.models.entity.Role;
import com.base.springsecurity.repository.RoleRepository;
import com.base.springsecurity.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service //Đánh dấu một Class là tầng Service, phục vụ các logic nghiệp vụ.

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findByRoleName(ERole roleName) {
        return roleRepository.findByName(roleName);
    }
}
