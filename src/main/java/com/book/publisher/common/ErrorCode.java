package com.book.publisher.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // Member
    ALLREADY_EXIST_MEMBER(400, "이미 존재하는 회원입니다.");

    private int status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
