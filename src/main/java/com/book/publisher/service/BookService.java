package com.book.publisher.service;

import com.book.publisher.entity.Book;
import com.book.publisher.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public Book saveBook(Book book) {

        return this.bookRepository.save(book);
    }
}
