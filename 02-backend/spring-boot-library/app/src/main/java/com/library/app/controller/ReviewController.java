package com.library.app.controller;


import com.library.app.entity.Book;
import com.library.app.requestmodels.ReviewsRequest;
import com.library.app.service.ReviewService;
import com.library.app.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController (ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value = "Authorization")
                            String token, @RequestParam Long bookId) throws  Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        if (userEmail == null){
            throw new Exception("User email is null");
        }
        return reviewService.userReviewListed(userEmail, bookId);
    }
    @PostMapping("/secure")
    public void postReview(@RequestHeader(value = "Authorization")
                           String token, @RequestBody ReviewsRequest reviewsRequest) throws  Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
       if (userEmail == null){
           throw new Exception("User email is null");
       }
       reviewService.postReview(userEmail, reviewsRequest);
    }
}
