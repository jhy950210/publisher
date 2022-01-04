package com.book.publisher;

import com.book.publisher.entity.Book;
import com.book.publisher.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@SpringBootTest
class PublisherApplicationTests {

	@Autowired
	BookService bookService;

	@Test
	void contextLoads() {
	}


	@Test
	@Transactional
	@Rollback(value = false)
	@DisplayName("책 정보 저장")
	void test_post_book() {
		Book book = new Book();
		book.setBookTitle("책1");
		book.setAuthor("작가");
		book.setPrice(0);
		book.setPublishDate(new Date(20220101));
		book.setSubTitle("부제1");

		Book newbook = bookService.saveBook(book);

		Assertions.assertThat(book.getBookTitle()).isEqualTo(newbook.getBookTitle());



	}
}
