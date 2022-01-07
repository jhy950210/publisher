package com.book.publisher.dto;

import lombok.Data;

@Data
public class BookSearchDTO {

    private String bookTitle;
    private String author;
    private int minPrice;
    private int maxPrice;

    public enum searchType {
        T,A,NP,XP
    }

    public BookSearchDTO(String bookTitle, String author, int minPrice, int maxPrice) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

}
