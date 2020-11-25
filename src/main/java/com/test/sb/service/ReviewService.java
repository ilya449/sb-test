package com.test.sb.service;

import com.test.sb.model.Review;
import com.test.sb.repository.ReviewRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements GeneralService<Review> {
    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAll(List<Review> reviews) {
        repository.saveAll(reviews);
    }
}
