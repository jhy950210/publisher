package com.book.publisher.service;

import com.book.publisher.entity.Member;
import com.book.publisher.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> getMembers(){
        List<Member> members = memberRepository.findAll();

        return members;
    }


}