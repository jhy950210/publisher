package com.book.publisher.api;

import com.book.publisher.entity.Book;
import com.book.publisher.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    @PostMapping(value = "/books")
    public ResponseEntity<Book> bookInsert(@RequestBody Book book) {

        Book newBook = bookService.saveBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> bookList() {
        List<Book> bookArrayList = bookService.bookList();

        return ResponseEntity.status(HttpStatus.OK).body(bookArrayList);
    }

//    @GetMapping("/books")
//    public ResponseEntity<Book> bookInfo(@RequestBody Book book) {
//        Book bookInfo = bookService.bookInfo(book.getId());
//
//        return ResponseEntity.status(HttpStatus.OK).body(bookInfo);
//    }

    @PutMapping("/books")
    public ResponseEntity<Book> bookUpdate(@RequestBody Book book) {
        Book newBook = bookService.bookInfo(book.getId());

        return ResponseEntity.status(HttpStatus.OK).body(newBook);
    }
}
