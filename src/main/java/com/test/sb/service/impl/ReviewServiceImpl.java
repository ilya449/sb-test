package com.test.sb.service.impl;

import com.test.sb.model.Review;
import com.test.sb.repository.ReviewRepository;
import com.test.sb.service.ReviewService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repository;

    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAll(List<Review> reviews) {
        repository.saveAll(reviews);
    }
}
