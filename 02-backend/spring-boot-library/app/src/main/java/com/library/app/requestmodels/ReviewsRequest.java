package com.library.app.requestmodels;

import lombok.Data;

import java.util.Optional;

@Data
public class ReviewsRequest {
    private double rating;

    private Long bookId;

    private Optional<String> reviewDescription;
}
