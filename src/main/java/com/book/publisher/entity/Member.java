package com.book.publisher.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String residentRegistrationNumber;

    @Embedded
    private Address address;

    @Builder
    public Member(String name, String email, String phoneNumber, String residentRegistrationNumber, Address address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.address = address;
    }
}