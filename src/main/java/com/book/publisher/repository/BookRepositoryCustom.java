package com.book.publisher.repository;

import com.book.publisher.entity.Book;

import java.util.ArrayList;

public interface BookRepositoryCustom {

    ArrayList<Book> findBookList();
}
