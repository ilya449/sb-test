package com.test.sb.service.impl;

import com.test.sb.model.Role;
import com.test.sb.repository.RoleRepository;
import com.test.sb.service.RoleService;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Role role) {
        repository.save(role);
    }

    @Override
    public Role findByName(Role.RoleName name) {
        return repository.getByRoleName(name).orElseThrow(NoSuchElementException::new);
    }
}
