package com.book.publisher.repository;

import com.book.publisher.dto.BookSearchDTO;
import com.book.publisher.entity.Book;
import com.book.publisher.entity.QBook;

import java.util.List;

public interface BookRepositoryCustom {

    List<Book> searchBookList(BookSearchDTO searchBook);

}
