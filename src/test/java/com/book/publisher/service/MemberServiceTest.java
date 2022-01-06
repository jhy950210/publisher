package com.book.publisher.service;

import com.book.publisher.entity.Address;
import com.book.publisher.entity.Member;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.validation.constraints.Null;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.*;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

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

        Member member2 = Member.builder()
                .name("test2")
                .email("test222@gmail.com")
                .phoneNumber("010-1234-1234")
                .residentRegistrationNumber("950210-2222222")
                .address(address)
                .build();

        memberService.join(member1);
        memberService.join(member2);
    }

    @Test
    @DisplayName("회원가입")
    void memberJoin(){
        //given
        Address address = Address.builder()
                .city("경기 고양시 일산서구")
                .street("킨텍스로 240")
                .zipcode("100동 1111호")
                .build();

        Member member3 = Member.builder()
                .name("test3")
                .email("test333@gmail.com")
                .phoneNumber("010-1234-1234")
                .residentRegistrationNumber("950210-3333333")
                .address(address)
                .build();

        //when
        Member join = memberService.join(member3);


        //then
        assertEquals(join.getName(),"test3");
    }

    @Test
    @DisplayName("회원 조회")
    void getMember(){
        //given
        Member member = memberService.getMember(2L);

        assertEquals(member.getName(), "test2");
    }

    @Test
    @DisplayName("회원 수정")
    void updateMember(){
        //given
        Member changeInfo = Member.builder()
                .email("test444@gmail.com")
                .phoneNumber("123-1234-1234")
                .build();

        //when
        Member member = memberService.updateMember(2L,changeInfo);
        Member member1 = memberService.getMember(2L);

        //then
        assertEquals(member1.getEmail(), member.getEmail());
    }

    @Test
    @DisplayName("회원 탈퇴")
    void deleteMember() throws NullPointerException {

        //when
        memberService.deleteMember(1L);

        //then
        assertThrows(NullPointerException.class, ()-> memberService.getMember(1L));
    }
}