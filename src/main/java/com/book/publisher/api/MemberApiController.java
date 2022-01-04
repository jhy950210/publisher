package com.book.publisher.api;

import com.book.publisher.entity.Member;
import com.book.publisher.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity getMembers(){
        List<Member> members = memberService.getMembers();

        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

}
