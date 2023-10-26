package com.lee.shop.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("회원 가입을 할 때 이미 존재하는 이메일이 있는지 검증한다.")
    @Test
    void existsByEmail(){

        // given
        Member member = Member.builder()
                .name("이찬복")
                .email("chanboklee@naver.com")
                .password("1234")
                .build();

        memberRepository.save(member);

        // when
        boolean result = memberRepository.existsByEmail(member.getEmail());

        // then
        assertThat(result).isTrue();
    }
}