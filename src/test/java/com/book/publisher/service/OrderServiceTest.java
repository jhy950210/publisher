package com.book.publisher.service;

import com.book.publisher.entity.*;
import com.book.publisher.repository.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    BookService bookService;

    @BeforeAll
    void init(){
        Address address = Address.builder()
                .city("경기 고양시 일산서구")
                .street("킨텍스로 240")
                .zipcode("100동 1111호")
                .build();

        Member member1 = Member.builder()
                .name("test1")
                .email("test111@gmail.com")
                .phoneNumber("010-1234-1234")
                .residentRegistrationNumber("950210-1111111")
                .address(address)
                .build();

        memberService.join(member1);

        Book book1 = new Book();
        book1.setBookTitle("책제목1");
        book1.setAuthor("작가1");
        book1.setPrice(1000);
        book1.setPublishDate(LocalDate.parse("2021-02-13"));
        book1.setSubTitle("부제1");

        Book book2 = new Book();
        book2.setBookTitle("책제목2");
        book2.setAuthor("작가2");
        book2.setPrice(2000);
        book2.setPublishDate(LocalDate.parse("2021-02-13"));
        book2.setSubTitle("부제2");

        bookService.saveBook(book1);
        bookService.saveBook(book2);

    }

    @Test
    @DisplayName("주문 생성")
    void createOrder(){
        // when
        Order order = orderService.order(1L, 2L, 3l);

        List<Order> allOrders = orderRepository.findAllOrders();

        for (Order allOrder : allOrders) {
            System.out.println("member = " + allOrder.getMember());
            System.out.println("book = " + allOrder.getOrderBooks());

        }
        // then
        assertEquals(OrderStatus.ORDER, order.getOrderStatus());
    }

    @Test
    @DisplayName("주문 취소")
    void cancelOrder(){
        // given
        Order order = orderService.order(1L, 2L, 3l);

        // when
        orderService.cancelOrder(order.getId());
        Order order1 = orderRepository.findById(order.getId()).get();

        // then
        assertEquals(OrderStatus.CANCEL, order1.getOrderStatus());
    }
}