package com.book.publisher.entity;


import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Entity
public class Book {
    @Id
    @Column(name = "book_id")
    private long id;

    private String bookTitle;

    private String subTitle;

    private int price;
    private String author;
    private Date publishDate;
    private Date regDt;
}
