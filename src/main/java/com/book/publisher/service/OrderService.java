package com.book.publisher.service;

import com.book.publisher.entity.Book;
import com.book.publisher.entity.Member;
import com.book.publisher.entity.Order;
import com.book.publisher.entity.OrderBook;
import com.book.publisher.repository.BookRepository;
import com.book.publisher.repository.MemberRepository;
import com.book.publisher.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public Order order(Long memberId, Long bookId){
        Member member = memberRepository.findById(memberId).orElseThrow(NullPointerException::new);

        Book book = bookRepository.findById(bookId).orElseThrow(NullPointerException::new);

        // 주문 책 생성
        OrderBook orderBook = OrderBook.createOrderBook(book);

        // 주문 생성
        Order order = Order.createOrder(member, orderBook);

        Order save = orderRepository.save(order);

        return save;
    }
}
