package com.test.sb.service;

import com.test.sb.model.Role;
import com.test.sb.repository.RoleRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleServiceTest {
    public static final String USER_ROLE_NAME = "USER";
    public static final int ONE = 1;
    @MockBean
    private RoleRepository repository;

    @Autowired
    private RoleService service;

    @Test
    public void addRole() {
        Role role = Role.of(USER_ROLE_NAME);

        service.save(role);

        Mockito.verify(repository, Mockito.times(ONE)).save(role);
    }

    @Test
    public void findByName() {
        Role expected = new Role();
        expected.setId((long) ONE);
        expected.setRoleName(Role.RoleName.USER);

        Mockito.when(repository.getByRoleName(Role.RoleName.USER))
                .thenReturn(Optional.of(expected));
        Role actual = service.findByName(Role.RoleName.USER);
        Assert.assertEquals(expected, actual);
        Mockito.verify(repository, Mockito.times(ONE))
                .getByRoleName(Role.RoleName.USER);
    }

    @Test(expected = NoSuchElementException.class)
    public void findAbsentRole() {
        Mockito.when(repository.getByRoleName(Role.RoleName.ADMIN))
                .thenReturn(Optional.empty());
        service.findByName(Role.RoleName.ADMIN);
        Mockito.verify(repository, Mockito.times(ONE))
                .getByRoleName(Role.RoleName.ADMIN);
    }
}
