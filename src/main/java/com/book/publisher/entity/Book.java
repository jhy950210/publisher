package com.book.publisher.entity;


import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicInsert
public class Book {
    @Id @GeneratedValue
    @Column(name = "book_id")
    private long id;

    private String bookTitle;

    private String subTitle;

    private int price;
    private String author;

    @Column(columnDefinition = "timestamp(0)")
    private Date publishDate;

    @ColumnDefault("current_timestamp")
    private Date regDt;
}
