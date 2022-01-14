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

    @Transactional
    public Order order(Long memberId, Long... bookIds){
        Member member = memberRepository.findById(memberId).orElseThrow(NullPointerException::new);

        List<OrderBook> orderBooks = new ArrayList<>();

        for (Long bookId : bookIds) {
            Book book = bookRepository.findById(bookId).orElseThrow(NullPointerException::new);

            // 주문 책 생성
            OrderBook orderBook = OrderBook.createOrderBook(book);

            orderBooks.add(orderBook);
        }

        int size = orderBooks.size();
        OrderBook[] toArray = new OrderBook[size];

        for(int i=0; i<size; i++){
            toArray[i] = orderBooks.get(i);
        }

        // 주문 생성
        Order order = Order.createOrder(member, toArray);

        Order save = orderRepository.save(order);

        return save;
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
