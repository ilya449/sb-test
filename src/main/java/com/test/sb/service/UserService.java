package com.test.sb.service;

import com.test.sb.model.User;
import com.test.sb.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService implements GeneralService<User> {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAll(List<User> items) {
        repository.saveAll(items);
    }
}
