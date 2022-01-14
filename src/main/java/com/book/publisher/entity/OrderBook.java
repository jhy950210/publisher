package com.book.publisher.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class OrderBook {

    @Id @GeneratedValue
    @Column(name = "order_book_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private int count;

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    // 주문 책 생성
    public static OrderBook createOrderBook(Book book){
        OrderBook orderBook = new OrderBook();
        orderBook.setBook(book);

        return orderBook;
    }

    // 주문 책 취소
    public void cancel() {
        this.getBook().addStock(count);
    }

    // 총 책 가격 조회
    public int getTotalPrice(){
        return this.getBook().getPrice() * getCount();
    }
}
