package com.test.sb.service.mapper;

import com.test.sb.dto.ReviewDto;
import com.test.sb.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    public static final String EXTERNAL_ID = "B001E4KFG0";
    public static final String PROFILE_NAME = "delmartian";

    @Autowired
    private GeneralMapper<User> mapper;

    @Test
    public void mapUser_OK() {
        User expected = User.builder()
                .externalId(EXTERNAL_ID)
                .profileName(PROFILE_NAME)
                .build();

        ReviewDto dto = ReviewDto.builder()
                .userId(EXTERNAL_ID)
                .profileName(PROFILE_NAME)
                .build();

        User actual = mapper.getEntity(dto);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void mapEmptyProduct() {
        ReviewDto dto = ReviewDto.builder().build();
        mapper.getEntity(dto);
    }
}
