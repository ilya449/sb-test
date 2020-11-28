package com.test.sb.service;

import com.test.sb.model.Role;

public interface RoleService {
    void save(Role role);

    Role findByName(Role.RoleName name);
}
