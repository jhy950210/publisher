package com.book.publisher.repository;

import com.book.publisher.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.ArrayList;

@Repository
public interface BookRepository extends JpaRepository<Book, Id> , BookRepositoryCustom{

    ArrayList<Book> findByBookTitleLike(String bookTitle);

}
