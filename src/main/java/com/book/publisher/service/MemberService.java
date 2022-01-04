package com.book.publisher.service;

import com.book.publisher.entity.Member;
import com.book.publisher.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> getMembers(){
        List<Member> members = memberRepository.findAll();

        return members;
    }

    @Transactional
    public void join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByResidentRegistrationNumber(member.getResidentRegistrationNumber());

        findMember.orElseThrow(() -> new IllegalStateException("이미 존재하는 회원입니다."));
    }

}