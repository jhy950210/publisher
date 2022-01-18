package com.book.publisher.service;

import com.book.publisher.entity.*;
import com.book.publisher.repository.BookRepository;
import com.book.publisher.repository.MemberRepository;
import com.book.publisher.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    // 기본 주문 생성
    @Transactional
    public Order order(Long memberId, Long bookId, int count){
        Member member = memberRepository.findById(memberId).orElseThrow(NullPointerException::new);

        Book book = bookRepository.findById(bookId).orElseThrow(NullPointerException::new);

        // 주문 책 생성
        OrderBook orderBook = OrderBook.createOrderBook(book, count);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderBook);

        Order saveOrder = orderRepository.save(order);

        return saveOrder;
    }

    // 배송 주소 직접 입력한 주문 생성
    @Transactional
    public Order order(Long memberId, Address address, Long bookId, int count){
        Member member = memberRepository.findById(memberId).orElseThrow(NullPointerException::new);

        Book book = bookRepository.findById(bookId).orElseThrow(NullPointerException::new);

        // 주문 책 생성
        OrderBook orderBook = OrderBook.createOrderBook(book, count);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(address);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderBook);

        Order saveOrder = orderRepository.save(order);

        return saveOrder;
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findById(orderId).orElseThrow(NullPointerException::new);

        //주문 취소
        order.cancel();
    }
}
