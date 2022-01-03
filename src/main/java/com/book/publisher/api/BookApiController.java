package com.book.publisher.api;

import com.book.publisher.entity.Book;
import com.book.publisher.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    @PostMapping(value = "/bookInsert")
    public ResponseEntity<Book> bookInsert(@RequestBody Book book) {

        Book newBook = bookService.saveBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }
}
