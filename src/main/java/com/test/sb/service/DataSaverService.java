package com.test.sb.service;

import com.test.sb.dto.ReviewDto;
import com.test.sb.model.Product;
import com.test.sb.model.Review;
import com.test.sb.model.User;
import java.util.List;

public interface DataSaverService {
    public List<User> saveUsers(List<ReviewDto> dtos);

    public List<Product> saveProducts(List<ReviewDto> dtos);

    public List<Review> saveReviews(List<ReviewDto> dtos);
}
