package com.book.publisher.api;

import com.book.publisher.entity.Book;
import com.book.publisher.service.BookService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookApiControllerTest {

    @Autowired
    BookService bookService;

    @RepeatedTest(value = 5)
    @BeforeAll
    static void bookEntity(@Autowired BookService bookService) {
        int i = 0;
        Book book = new Book();
        ++i;
        book.setBookTitle("책제목"+i);
        book.setAuthor("작가"+i);
        book.setPrice(1000);
        book.setPublishDate(LocalDate.parse("2021-02-13").plusDays(i));
        book.setSubTitle("부제"+i);

        bookService.saveBook(book);

        List<Book> bookList = bookService.bookList();

        assertThat(i).isEqualTo(i);
    }

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

        assertThat(bookList.get(0).getBookTitle()).isEqualTo(bookService.bookInfo((long)1).getBookTitle());
    }
}