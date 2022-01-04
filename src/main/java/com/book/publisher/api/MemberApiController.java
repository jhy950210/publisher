package com.book.publisher.api;

import com.book.publisher.entity.Address;
import com.book.publisher.entity.Member;
import com.book.publisher.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/members")
    public ResponseEntity postMember(@RequestBody @Valid Member member, BindingResult result){
        Member joinMember = memberService.join(member);

        return ResponseEntity.status(HttpStatus.CREATED).body(joinMember);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity putMember(@PathVariable("id") Long id,
                                    @RequestBody @Valid UpdateMemberRequest request,
                                    BindingResult result)
    {
        Member member = Member.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .build();

        Member updateMember = memberService.updateMember(id, member);

        return ResponseEntity.status(HttpStatus.CREATED).body(updateMember);
    }

    @Data
    static class UpdateMemberRequest{
        private String email;
        private String phoneNumber;
        private Address address;
    }
}
