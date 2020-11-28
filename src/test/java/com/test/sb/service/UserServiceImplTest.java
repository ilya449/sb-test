package com.test.sb.service;

import com.test.sb.model.Role;
import com.test.sb.model.User;
import com.test.sb.repository.UserRepository;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    public static final int ONE = 1;
    public static final String PASSWORD = "pass123";
    public static final Set<Role> USER_ROLE_SET = Set.of(Role.of("USER"));
    public static final String FIRST_PROFILE_NAME = "delmartian";
    public static final String SECOND_PROFILE_NAME = "dll pa";

    @MockBean
    private UserRepository repository;
    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void saveAll() {
        User firstUser = User.builder()
                .profileName(FIRST_PROFILE_NAME)
                .password(encoder.encode(PASSWORD))
                .roles(USER_ROLE_SET)
                .build();
        User secondUser = User.builder()
                .profileName(SECOND_PROFILE_NAME)
                .password(encoder.encode(PASSWORD))
                .roles(USER_ROLE_SET)
                .build();

        List<User> users = List.of(firstUser, secondUser);

        service.saveAll(users);
        Mockito.verify(repository, Mockito.times(ONE)).saveAll(users);
    }
}
