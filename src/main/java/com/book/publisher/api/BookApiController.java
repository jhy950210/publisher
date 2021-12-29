package com.book.publisher.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookApiController {

    @GetMapping("/bookInsert")
    public String bookInsert() {
        return "book insert";
    }
}
