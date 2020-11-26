package com.test.sb.service;

import com.test.sb.model.Role;
import com.test.sb.repository.RoleRepository;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public void save(Role role) {
        repository.save(role);
    }

    public Role findByName(Role.RoleName name) {
        return repository.getByRoleName(name).orElseThrow(NoSuchElementException::new);
    }
}
