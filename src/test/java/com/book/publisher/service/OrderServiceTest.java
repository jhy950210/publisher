package com.book.publisher.service;

import com.book.publisher.entity.*;
import com.book.publisher.repository.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
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

        Book book = new Book();
        book.setBookTitle("책제목");
        book.setAuthor("작가");
        book.setPrice(1000);
        book.setPublishDate(LocalDate.parse("2021-02-13"));
        book.setSubTitle("부제");

        bookService.saveBook(book);

    }

    @Test
    @DisplayName("주문 생성")
    void createOrder(){
        // when
        Order order = orderService.order(1L, 2L);

        List<Order> allOrders = orderRepository.findAllOrders();

        for (Order allOrder : allOrders) {
            System.out.println("member = " + allOrder.getMember());
            System.out.println("book = " + allOrder.getOrderBooks());

        }
        // then
        assertEquals(OrderStatus.ORDER, order.getOrderStatus());
    }
}