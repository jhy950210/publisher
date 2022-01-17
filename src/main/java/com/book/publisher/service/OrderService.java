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
    public Order order(Long memberId, Long... bookIds){
        Member member = memberRepository.findById(memberId).orElseThrow(NullPointerException::new);

        List<OrderBook> orderBooks = new ArrayList<>();

        int size = orderBooks.size();
        OrderBook[] convertOrderBooksListtoArray = new OrderBook[size];

        // 주문 책 생성
        for(int i=0; i<size; i++){
            convertOrderBooksListtoArray[i] = orderBooks.get(i);
        }

        for (Long bookId : bookIds) {
            Book book = bookRepository.findById(bookId).orElseThrow(NullPointerException::new);

            OrderBook orderBook = OrderBook.createOrderBook(book, size);

            orderBooks.add(orderBook);
        }

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());



        // 주문 생성
        Order order = Order.createOrder(member, delivery, convertOrderBooksListtoArray);

        Order saveOrder = orderRepository.save(order);

        return saveOrder;
    }

    // 배송 주소 직접 입력한 주문 생성
    @Transactional
    public Order order(Long memberId, Address address,Long... bookIds){
        Member member = memberRepository.findById(memberId).orElseThrow(NullPointerException::new);

        List<OrderBook> orderBooks = new ArrayList<>();

        int size = orderBooks.size();
        OrderBook[] convertOrderBooksListtoArray = new OrderBook[size];

        // 주문 책 생성
        for(int i=0; i<size; i++){
            convertOrderBooksListtoArray[i] = orderBooks.get(i);
        }

        for (Long bookId : bookIds) {
            Book book = bookRepository.findById(bookId).orElseThrow(NullPointerException::new);

            OrderBook orderBook = OrderBook.createOrderBook(book, size);

            orderBooks.add(orderBook);
        }

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(address);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, convertOrderBooksListtoArray);

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
