package com.book.publisher.common;

import com.book.publisher.exception.NotExistMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    private ResponseEntity handleIllegalStateException(){
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.BAD_REQUEST);
        HttpStatus httpStatus = HttpStatus.valueOf(errorResponse.getStatus());

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    // 수정 필요 -- 현재 멤버 조회시 멤버가 존재하지 않을때 처리하는 로직으로 사용중
    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity handleNullPointerException(){
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.NOT_EXIST_MEMBER);
        HttpStatus httpStatus = HttpStatus.valueOf(errorResponse.getStatus());

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @ExceptionHandler(NotExistMemberException.class)
    private ResponseEntity handleNotExistMemberException(){
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.NOT_EXIST_MEMBER);
        HttpStatus httpStatus = HttpStatus.valueOf(errorResponse.getStatus());

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity handleIllegalArgumentExceptio(){
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.BAD_REQUEST);
        HttpStatus httpStatus = HttpStatus.valueOf(errorResponse.getStatus());

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
