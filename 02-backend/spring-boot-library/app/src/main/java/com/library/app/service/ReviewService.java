package com.library.app.service;

import com.library.app.dao.BookRepository;
import com.library.app.dao.ReviewRepository;
import com.library.app.entity.Review;
import com.library.app.requestmodels.ReviewsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;


@Service
@Transactional
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void postReview(String userEmail, ReviewsRequest reviewsRequest) throws Exception{
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, reviewsRequest.getBookId());
        if (validateReview != null){
            throw new Exception("Review already created");
        }
        Review review = new Review();
        review.setBookId(reviewsRequest.getBookId());
        review.setRating(reviewsRequest.getRating());
        review.setUserEmail(userEmail);
        if (reviewsRequest.getReviewDescription().isPresent()){
            review.setReviewDescription(reviewsRequest.getReviewDescription().map(
                    Object::toString
            ).orElse(null));
        }
        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRepository.save(review);
    }
    public Boolean userReviewListed(String userEmail, Long bookId) {
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
        if (validateReview != null) {
            return true;
        } else {
            return false;
        }
    }
}
