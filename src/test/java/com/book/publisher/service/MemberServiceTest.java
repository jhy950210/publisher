package com.book.publisher.service;

import com.book.publisher.entity.Address;
import com.book.publisher.entity.Member;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @BeforeEach
    void init(){
        Address address = Address.builder()
                .city("경기 고양시 일산서구")
                .street("킨텍스로 240")
                .zipcode("100동 1111호")
                .build();

        Member member1 = Member.builder()
                .name("test2")
                .email("test222@gmail.com")
                .phoneNumber("010-1234-1234")
                .residentRegistrationNumber("950210-2222222")
                .address(address)
                .build();

        Member member2 = Member.builder()
                .name("test3")
                .email("test3@gmail.com")
                .phoneNumber("010-1234-1234")
                .residentRegistrationNumber("950210-3333333")
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
                .name("test1")
                .email("test111@gmail.com")
                .phoneNumber("010-1234-1234")
                .residentRegistrationNumber("950210-1111111")
                .address(address)
                .build();

        //when
        memberService.join(member3);

        //then
        assertEquals(memberService.getMembers().size(),3);
    }

    @Test
    @DisplayName("회원 불러오기")
    void getMember(){
        //given
        Member member = memberService.getMember(1L);

        assertEquals(member.getName(), "test2");
    }
}