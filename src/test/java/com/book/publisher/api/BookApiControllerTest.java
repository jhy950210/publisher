package com.book.publisher.api;

import com.book.publisher.entity.Book;
import com.book.publisher.service.BookService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookApiControllerTest {

    @Autowired
    BookService bookService;

//    @RepeatedTest(value = 5)
//    @BeforeAll
//    static void bookEntity(@Autowired BookService bookService,RepetitionInfo ri) {
//        Book book = new Book();
//        book.setBookTitle("책제목"+ ri.getCurrentRepetition());
//        book.setAuthor("작가"+ri.getCurrentRepetition());
//        book.setPrice(1000);
//        book.setPublishDate(LocalDate.parse("2021-02-13").plusDays(ri.getCurrentRepetition()));
//        book.setSubTitle("부제"+ri.getCurrentRepetition());
//
//        Book newBook = bookService.saveBook(book);
//
//        assertThat(book.getBookTitle()).isEqualTo(newBook.getBookTitle());
//    }

    @Test
    @DisplayName("책 저장")
    void bookInsert() {
        Book book = new Book();
            book.setBookTitle("책제목");
            book.setAuthor("작가");
            book.setPrice(1000);
            book.setPublishDate(LocalDate.parse("2021-02-13"));
            book.setSubTitle("부제");

        Book newBook = bookService.saveBook(book);


        assertThat(book.getBookTitle()).isEqualTo(bookService.bookInfo(newBook.getId()).getBookTitle());


    }

    @Test
    @DisplayName("책 리스트 검색")
    void bookList() {
        List<Book> bookList = bookService.bookList();

        assertThat(bookList.get(0).getBookTitle()).isEqualTo(bookService.bookInfo(bookList.get(0).getId()).getBookTitle());
    }

    @Test
    @DisplayName("책 수정")
    void bookUpdate() {
        Book books = bookService.bookList().get(0);
        Book book = bookService.bookInfo(books.getId());

        book.setBookTitle("책 제목 수정");

        Book newBook = bookService.saveBook(book);

        assertThat(book.getBookTitle())
                .isEqualTo(newBook.getBookTitle());
    }

    @Test
    @DisplayName("책 삭제")
    void bookDelete() {
        for(int i=0;i<5;i++) {
            Book book = new Book();
            book.setBookTitle("책제목"+i);
            book.setAuthor("작가"+i);
            book.setPrice(i*1000);
            book.setPublishDate(LocalDate.parse("2021-02-13").plusDays(i));
            book.setSubTitle("부제"+i);
        }

        int random = (int)(Math.random()*5)+1;

        Book newBook = bookService.bookInfo((long)random);
        bookService.bookDelete(newBook);

        assertThat(bookService.bookInfo((long)random).getBookTitle()).isNull();


    }
}