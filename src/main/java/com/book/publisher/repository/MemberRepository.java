package com.book.publisher.repository;

import com.book.publisher.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByResidentRegistrationNumber(String rrn);

    Optional<Member> findByName(String name);
}
