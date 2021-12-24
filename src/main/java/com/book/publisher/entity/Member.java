package com.book.publisher.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String residentRegistrationNumber;

    @Embedded
    private Address address;
}