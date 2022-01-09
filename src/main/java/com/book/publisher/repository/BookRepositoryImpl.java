package com.book.publisher.repository;

import com.book.publisher.dto.BookSearchDTO;
import com.book.publisher.entity.Book;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.*;
import static com.book.publisher.entity.QBook.*;

public class BookRepositoryImpl implements BookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BookRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Book> searchBookList(BookSearchDTO searchBook) {

        return queryFactory
                .selectFrom(book)
                .where(containsBookTitle(searchBook.getBookTitle())
                ,containsAuthor(searchBook.getAuthor())
                ,goePrice(searchBook.getMinPrice())
                ,loePrice(searchBook.getMaxPrice())
                )
                .orderBy(book.id.desc())
                .fetch();
    }

    private BooleanExpression containsBookTitle(String title) {
        return hasText(title) ? book.bookTitle.contains(title) : null;
    }
    private BooleanExpression containsAuthor(String author) {
        return hasText(author) ? book.author.contains(author) : null;
    }
    private BooleanExpression goePrice(Integer minPrice) {
        return minPrice != 0 ? book.price.goe(minPrice) : null;
    }
    private BooleanExpression loePrice(Integer maxPrice) {
        return maxPrice != 0 ? book.price.loe(maxPrice) : null;
    }
}
