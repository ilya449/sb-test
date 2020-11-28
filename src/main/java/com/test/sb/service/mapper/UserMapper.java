package com.test.sb.service.mapper;

import com.test.sb.dto.ReviewDto;
import com.test.sb.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements GeneralMapper<User> {
    @Override
    public User getEntity(ReviewDto dto) {
        return User.builder()
                .externalId(dto.getUserId())
                .profileName(dto.getProfileName())
                .build();
    }
}
