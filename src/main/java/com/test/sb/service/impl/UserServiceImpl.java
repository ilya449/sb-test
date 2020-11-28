package com.test.sb.service.impl;

import com.test.sb.model.User;
import com.test.sb.repository.UserRepository;
import com.test.sb.service.UserService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAll(List<User> users) {
        repository.saveAll(users);
    }

    @Override
    public User findByExternalId(String id) {
        return repository.findUserByExternalId(id).get();
    }
}
