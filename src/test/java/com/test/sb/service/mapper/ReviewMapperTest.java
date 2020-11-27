package com.test.sb.service.mapper;

import com.test.sb.dto.ReviewDto;
import com.test.sb.model.Product;
import com.test.sb.model.Review;
import com.test.sb.model.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReviewMapperTest {
    public static final LocalDateTime TIME = LocalDateTime
            .ofInstant(Instant.ofEpochSecond(1303862400), ZoneId.systemDefault());
    public static final String PRODUCT_ID = "B001E4KFG0";
    public static final String USER_ID = "A3SGXH7AUHU8GW";
    public static final String PROFILE_NAME = "delmartian";
    public static final int ONE_NUMBER = 1;
    public static final int SCORE_5 = 5;
    public static final String SUMMARY = "Good Quality Dog Food";
    public static final String TEXT = "I have bought several of the Vitality canned dog food"
            + " products and have found them all to be of good quality. The product looks"
            + " more like a stew than a processed meat and it smells better. My Labrador"
            + " is finicky and she appreciates this product better than  most.";

    @Autowired
    private GeneralMapper<Review> reviewMapper;
    @Autowired
    private GeneralMapper<Product> productMapper;
    @Autowired
    private GeneralMapper<User> userMapper;

    @Test
    public void mapReview_OK() {
        ReviewDto dto = ReviewDto.builder()
                .id((long) ONE_NUMBER)
                .productId(PRODUCT_ID)
                .userId(USER_ID)
                .profileName(PROFILE_NAME)
                .helpfulnessNumerator(ONE_NUMBER)
                .helpfulnessDenominator(ONE_NUMBER)
                .score(SCORE_5)
                .time(TIME)
                .summary(SUMMARY)
                .text(TEXT)
                .build();

        Review expected = Review.builder()
                .externalId((long) ONE_NUMBER)
                .helpfulnessNumerator(ONE_NUMBER)
                .helpfulnessDenominator(ONE_NUMBER)
                .score(SCORE_5)
                .time(TIME)
                .summary(SUMMARY)
                .text(TEXT)
                .product(productMapper.getEntity(dto))
                .user(userMapper.getEntity(dto))
                .build();

        Review actual = reviewMapper.getEntity(dto);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mapOnlyWithRequiredFields() {
        ReviewDto dto = ReviewDto.builder()
                .id((long) ONE_NUMBER)
                .productId(PRODUCT_ID)
                .userId(USER_ID)
                .profileName(PROFILE_NAME)
                .time(TIME)
                .build();

        Review expected = Review.builder()
                .externalId((long) ONE_NUMBER)
                .time(TIME)
                .product(productMapper.getEntity(dto))
                .user(userMapper.getEntity(dto))
                .build();

        Review actual = reviewMapper.getEntity(dto);

        Assert.assertEquals(expected, actual);
    }
}
