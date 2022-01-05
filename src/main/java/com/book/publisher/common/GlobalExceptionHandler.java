package com.book.publisher.common;

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

    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity handleNullPointerException(){
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.NOT_EXIST_MEMBER);
        HttpStatus httpStatus = HttpStatus.valueOf(errorResponse.getStatus());

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
