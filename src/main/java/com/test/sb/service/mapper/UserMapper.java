package com.test.sb.service.mapper;

import com.test.sb.dto.ReviewDto;
import com.test.sb.model.Role;
import com.test.sb.model.User;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements GeneralMapper<User> {
    private static final Set<Role> DEFAULT_ROLE = Set.of(Role.of("USER"));

    @Value("${default.user.password}")
    private static String DEFAULT_PASSWORD;

    @Override
    public User getEntity(ReviewDto dto) {
        return User.builder()
                .externalId(dto.getUserId())
                .profileName(dto.getProfileName())
                .roles(DEFAULT_ROLE)
                .password(new BCryptPasswordEncoder().encode(DEFAULT_PASSWORD))
                .build();
    }
}
