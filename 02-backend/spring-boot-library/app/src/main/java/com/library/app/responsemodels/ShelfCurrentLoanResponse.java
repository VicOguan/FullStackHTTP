package com.library.app.responsemodels;

import com.library.app.entity.Book;
import lombok.Data;

@Data
public class ShelfCurrentLoanResponse {

    public ShelfCurrentLoanResponse(Book book, int daysLeft){
        this.book = book;
        this.daysLeft = daysLeft;
    }
    private Book book;

    private int daysLeft;
}
