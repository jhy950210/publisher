package com.book.publisher.repository;

import com.book.publisher.entity.Order;
import com.book.publisher.entity.QMember;
import com.book.publisher.entity.QOrder;
import com.book.publisher.entity.QOrderBook;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {

        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Order> findAllOrders(){
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QOrderBook orderBook = QOrderBook.orderBook;

        return queryFactory.select(order)
                .from(order)
                .leftJoin(order.member, member)
                .fetchJoin()
                .leftJoin(order.orderBooks,orderBook)
                .fetchJoin()
                .fetch();
    }
}
