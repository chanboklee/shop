package com.lee.shop.api.service.member;

import com.lee.shop.api.service.member.request.MemberSaveServiceRequest;
import com.lee.shop.api.service.member.response.MemberSaveResponse;
import com.lee.shop.domain.member.Member;
import com.lee.shop.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    private MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
    }

    @DisplayName("성명, 이메일, 패스워드를 입력 받아 회원가입을 한다.")
    @Test
    void saveMember(){
        // given
        MemberSaveServiceRequest memberSaveServiceRequest = MemberSaveServiceRequest.builder()
                .name("이찬복")
                .email("chanboklee@naver.com")
                .password("1234")
                .build();

        // when
        MemberSaveResponse memberSaveResponse = memberService.saveMember(memberSaveServiceRequest);

        // then
        assertThat(memberSaveResponse.getId()).isNotNull();
        assertThat(memberSaveResponse)
                .extracting("name", "email", "password")
                .contains("이찬복", "chanboklee@naver.com", "1234");
    }

    @DisplayName("이미 존재하는 이메일이 있으면 회원가입에 실패한다.")
    @Test
    void validateDuplicateMember(){
        // given
        Member member = Member.builder()
                .name("이찬복")
                .email("chanboklee@naver.com")
                .password("1234")
                .build();

        memberRepository.save(member);

        // when // then
        MemberSaveServiceRequest memberSaveServiceRequest = MemberSaveServiceRequest.builder()
                .name("홍길동")
                .email("chanboklee@naver.com")
                .password("4321")
                .build();

        assertThatThrownBy(() -> memberService.saveMember(memberSaveServiceRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 회원입니다.");
    }
}