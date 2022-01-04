package com.book.publisher.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // Member
    BAD_REQUEST(400, "요구된 처리를 하기에 적합한 상태가 아닙니다.");

    private int status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
