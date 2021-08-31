package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public Review findReviewById(Long id) {
        return reviewRepository.findById(id).get();
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }
}
