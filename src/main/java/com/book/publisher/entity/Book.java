package com.book.publisher.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    long bookId;
    String bookName;
    String author;
    long price;
}
