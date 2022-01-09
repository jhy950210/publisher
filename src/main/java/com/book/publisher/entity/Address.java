package com.book.publisher.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Address {

    private String city;
    private String street;
    private String zipcode;

}
