package com.book.publisher.service;

import com.book.publisher.entity.*;
import com.book.publisher.entity.Order;
import com.book.publisher.repository.BookRepository;
import com.book.publisher.repository.MemberRepository;
import com.book.publisher.repository.OrderRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("주문 생성")
    public void createOrder() throws Exception{
        //given
        Member member = createMember("testOrder","testOrder@gmail.com");

        Book book = createBook("주문책");

        //when
        Order order = orderService.order(member.getId(), book.getId(),2);

        //then
        Order getOrder = orderRepository.findById(order.getId()).get();

        assertEquals(OrderStatus.ORDER, getOrder.getOrderStatus());
    }

    @Test
    @DisplayName("주문 취소")
    public void cancelOrder() throws Exception{
        //given
        Member member = createMember("testOrder","testOrder@gmail.com");

        Book book = createBook("주문책");

        Order order = orderService.order(member.getId(), book.getId(),2);

        //when
        orderService.cancelOrder(order.getId());

        //then
        Order getOrder = orderRepository.findById(order.getId()).get();

        assertEquals(OrderStatus.CANCEL, getOrder.getOrderStatus());

    }

    private Member createMember(String name, String email) {
        Address address = Address.builder()
                .city("경기 고양시 일산서구")
                .street("킨텍스로 240")
                .zipcode("100동 1111호")
                .build();

        Member member = Member.builder()
                .name(name)
                .email(email)
                .phoneNumber("010-1234-1234")
                .residentRegistrationNumber("950210-1111111")
                .address(address)
                .build();

        Member save = memberRepository.save(member);

        return save;
    }

    private Book createBook(String name) {
        Book book = new Book();
        book.setBookTitle(name);
        book.setAuthor("작가1");
        book.setStockQuantity(10);
        book.setPrice(1000);
        book.setPublishDate(LocalDate.parse("2021-02-13"));
        book.setSubTitle("부제1");

        Book save = bookRepository.save(book);

        return save;
    }
}