package com.book.publisher.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    private ResponseEntity handleIllegalStateException(IllegalStateException e){
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.ALLREADY_EXIST_MEMBER);
        HttpStatus httpStatus = HttpStatus.valueOf(errorResponse.getStatus());

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
