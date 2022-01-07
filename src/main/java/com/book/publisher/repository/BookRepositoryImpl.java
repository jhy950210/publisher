package com.book.publisher.repository;

import com.book.publisher.entity.Book;
import com.book.publisher.entity.QBook;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class BookRepositoryImpl implements BookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BookRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Book> searchBookList(QBook book) {
        return queryFactory
                .selectFrom(book)
                .where()
                .fetch();
    }


}
