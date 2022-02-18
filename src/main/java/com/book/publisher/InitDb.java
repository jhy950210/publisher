package com.book.publisher;

import com.book.publisher.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.InitAdmin();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        private final BCryptPasswordEncoder passwordEncoder;

        public void InitAdmin(){
            String encodePassword = passwordEncoder.encode("1234");

            Member member = Member.builder()
                    .name("admin")
                    .password(encodePassword)
                    .phoneNumber("010-1234-1234")
                    .email("admin@admin.com")
                    .residentRegistrationNumber("950210-1234567")
                    .build();

            em.persist(member);
        }
    }
}
