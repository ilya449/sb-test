package com.test.sb.service;

import com.test.sb.model.Product;
import com.test.sb.model.Review;
import com.test.sb.model.Role;
import com.test.sb.model.User;
import com.test.sb.repository.ReviewRepository;
import java.time.LocalDateTime;
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
public class ReviewServiceImplTest {
    public static final int ONE = 1;
    public static final String FIRST_PRODUCT_ID = "B001E4KFG0";
    public static final String SECOND_PRODUCT_ID = "B00813GRG4";
    public static final String PASSWORD = "pass123";
    public static final Set<Role> USER_ROLE_SET = Set.of(Role.of("USER"));
    public static final String FIRST_PROFILE_NAME = "delmartian";
    public static final String SECOND_PROFILE_NAME = "dll pa";

    @MockBean
    private ReviewRepository repository;
    @Autowired
    private ReviewService service;
    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void saveAll() {
        Review firstReview = Review.builder()
                .time(LocalDateTime.now())
                .user(User.builder()
                        .profileName(FIRST_PROFILE_NAME)
                        .password(encoder.encode(PASSWORD))
                        .roles(USER_ROLE_SET)
                        .build())
                .product(new Product(FIRST_PRODUCT_ID))
                .build();

        Review secondReview = Review.builder()
                .time(LocalDateTime.now())
                .user(User.builder()
                        .profileName(SECOND_PROFILE_NAME)
                        .password(encoder.encode(PASSWORD))
                        .roles(USER_ROLE_SET)
                        .build())
                .product(new Product(SECOND_PRODUCT_ID))
                .build();

        List<Review> reviews = List.of(firstReview, secondReview);

        service.saveAll(reviews);
        Mockito.verify(repository, Mockito.times(ONE)).saveAll(reviews);
    }
}
