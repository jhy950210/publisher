package com.book.publisher.repository;

import com.book.publisher.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.ArrayList;

public interface BookRepository extends JpaRepository<Book, Id> , BookRepositoryCustom{

    ArrayList<Book> findByBookTitleLike(String bookTitle);

}
