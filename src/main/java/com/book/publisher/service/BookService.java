package com.book.publisher.service;

import com.book.publisher.entity.Book;
import com.book.publisher.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor

public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public Book saveBook(Book book) {

        return this.bookRepository.save(book);
    }

    @Transactional
    public List<Book> bookList() {
        return this.bookRepository.findAll();
    }

    @Transactional
    public Book bookInfo(Long id) {
        return this.bookRepository.findById(id).orElse(new Book());
    }

    @Transactional
    public void bookDelete(Book book) {
        this.bookRepository.delete(book);
    }

    @Transactional
    public List<Book> bookSearchList(Book book) {
        return this.bookRepository.findByBookTitleContainsAndAuthorOrderByRegDt(book.getBookTitle(),book.getAuthor());
    }
}
