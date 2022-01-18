package com.book.publisher.api;

import com.book.publisher.dto.BookSearchDTO;
import com.book.publisher.entity.Book;
import com.book.publisher.repository.BookRepository;
import com.book.publisher.service.BookService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BookApiControllerTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void init(){

        for(int i=1;i<100;i++) {
            Book book = new Book();
            book.setBookTitle("책제목"+i);
            book.setAuthor("작가"+i);
            book.setPrice(1000*i);
            book.setPublishDate(LocalDate.parse("2021-02-13").plusDays(i));
            book.setSubTitle("부제"+i);
            bookRepository.save(book);
        }
    }

    @Test
    @DisplayName("책 리스트 검색")
    void bookList() {
        int random = (int)(Math.random()*5)+1;  // ?

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

    @Test
    @DisplayName("queryDsl 테스트")
    void queryDslTest() {
        BookSearchDTO searchDTO = BookSearchDTO.builder()
                .bookTitle("제목")
                .author("작가")
                .maxPrice(60000)
                .minPrice(30000)
                .build();

        List<Book> books = bookService.bookSearchList(searchDTO);

        Book newBook = bookService.bookInfo(books.get(0).getId());

        books.forEach(v -> System.out.println(v.getBookTitle() + " " + v.getPrice()));

        assertThat(books.get(0).getBookTitle()).isEqualTo(newBook.getBookTitle());

    }

    @AfterEach
    void clean(){
        bookRepository.deleteAll();
    }
}