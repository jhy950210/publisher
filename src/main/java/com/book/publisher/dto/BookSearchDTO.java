package com.book.publisher.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchDTO {

    private String bookTitle;
    private String author;
    private int minPrice;
    private int maxPrice;

    public enum bookSearchType {
        T,A,MNP,MXP;
    }
}
