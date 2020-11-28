package com.test.sb.service.impl;

import com.test.sb.dto.ReviewDto;
import com.test.sb.model.Product;
import com.test.sb.model.Review;
import com.test.sb.model.Role;
import com.test.sb.model.User;
import com.test.sb.service.DataSaverService;
import com.test.sb.service.ProductService;
import com.test.sb.service.ReviewService;
import com.test.sb.service.RoleService;
import com.test.sb.service.UserService;
import com.test.sb.service.mapper.GeneralMapper;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DataSaverServiceImpl implements DataSaverService {
    @Value("${default.user.password}")
    private String defaultPassword;

    private final PasswordEncoder encoder;
    private final UserService userService;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final GeneralMapper<User> userMapper;
    private final GeneralMapper<Product> productMapper;
    private final GeneralMapper<Review> reviewMapper;
    private final RoleService roleService;

    public DataSaverServiceImpl(PasswordEncoder encoder, UserService userService,
                                ProductService productService, ReviewService reviewService,
                                GeneralMapper<User> userMapper,
                                GeneralMapper<Product> productMapper,
                                GeneralMapper<Review> reviewMapper, RoleService roleService) {
        this.encoder = encoder;
        this.userService = userService;
        this.productService = productService;
        this.reviewService = reviewService;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
        this.reviewMapper = reviewMapper;
        this.roleService = roleService;
    }

    @Override
    public List<User> saveUsers(List<ReviewDto> dtos) {
        Set<Role> role = Set.of(roleService.findByName(Role.RoleName.USER));
        String encodedPassword = encoder.encode(defaultPassword);
        List<User> users = dtos.stream()
                .map(userMapper::getEntity)
                .peek(u -> {
                    u.setRoles(role);
                    u.setPassword(encodedPassword);
                })
                .collect(Collectors.toList());
        userService.saveAll(users);
        return users;
    }

    @Override
    public List<Product> saveProducts(List<ReviewDto> dtos) {
        List<Product> products = dtos.stream()
                .map(productMapper::getEntity)
                .collect(Collectors.toList());
        productService.saveAll(products);
        return products;
    }

    @Override
    public List<Review> saveReviews(List<ReviewDto> dtos) {
        List<Review> reviews = dtos.stream()
                .map(r -> {
                    Review review = reviewMapper.getEntity(r);
                    review.setUser(userService.findByExternalId(r.getUserId()));
                    review.setProduct(productService.findByExternalId(r.getProductId()));
                    return review;
                })
                .collect(Collectors.toList());
        reviewService.saveAll(reviews);
        return reviews;
    }
}
