package com.lee.shop.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 가입 테스트")
    void 회원_저장_테스트(){
        // given
        Member member = Member.builder().email("chanboklee@naver.com")
                .password("1234")
                .build();

        // when
        Member saveMember = memberRepository.save(member);

        // then
        Assertions.assertEquals(member.getEmail(), saveMember.getEmail());
        Assertions.assertEquals(member.getPassword(), saveMember.getPassword());

    }

    @Test
    @DisplayName("회원 단건 조회 테스트")
    void 회원_단건_조회_테스트(){
        // given
        Member member = Member.builder().email("chanboklee@naver.com")
                .password("1234")
                .build();

        memberRepository.save(member);

        // when
        Member findMember = memberRepository.findById(1L).orElse(null);

        // then
        Assertions.assertEquals(findMember.getEmail(), "chanboklee@naver.com");
        Assertions.assertEquals(findMember.getPassword(), "1234");
    }
}