package com.book.publisher.service;

import com.book.publisher.entity.Member;
import com.book.publisher.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    public Member getMember(Long id){
        Member member = memberRepository.findById(id).orElseThrow(NullPointerException::new);

        return member;
    }

    @Transactional
    public Member join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증

        Member saveMember = memberRepository.save(member);

        return saveMember;
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByResidentRegistrationNumber(member.getResidentRegistrationNumber());

        if(findMember.isPresent()){
            throw new IllegalStateException();
        }
    }

    @Transactional
    public Member updateMember(Long id, Member member){
        Member findMember = memberRepository.findById(id).orElseThrow(IllegalStateException::new);

        //findMember.changeInfo(member.getEmail(), member.getPhoneNumber(), member.getAddress());

        if(member.getEmail() != null){
            findMember.setEmail(member.getEmail());
        }

        if(member.getPhoneNumber() != null){
            findMember.setPhoneNumber(member.getPhoneNumber());
        }

        if(member.getAddress() != null){
            findMember.setAddress(member.getAddress());
        }

        return findMember;
    }

    @Transactional
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

}