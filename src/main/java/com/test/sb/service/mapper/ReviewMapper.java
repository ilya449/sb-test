package com.test.sb.service.mapper;

import com.test.sb.dto.ReviewDto;
import com.test.sb.model.Product;
import com.test.sb.model.Review;
import com.test.sb.model.User;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements GeneralMapper<Review> {
    private final GeneralMapper<User> userMapper;
    private final GeneralMapper<Product> productMapper;

    public ReviewMapper(UserMapper userMapper, ProductMapper productMapper) {
        this.userMapper = userMapper;
        this.productMapper = productMapper;
    }

    @Override
    public Review getEntity(ReviewDto dto) {
        return Review.builder()
                .user(userMapper.getEntity(dto))
                .product(productMapper.getEntity(dto))
                .externalId(dto.getId())
                .helpfulnessNumerator(dto.getHelpfulnessNumerator())
                .helpfulnessDenominator(dto.getHelpfulnessDenominator())
                .score(dto.getScore())
                .time(dto.getTime())
                .summary(dto.getSummary())
                .text(dto.getText())
                .build();
    }
}
