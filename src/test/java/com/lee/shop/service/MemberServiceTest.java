package com.lee.shop.service;

import com.lee.shop.domain.Member;
import com.lee.shop.domain.MemberRepository;
import com.lee.shop.dto.MemberListResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 가입 서비스 테스트")
    void 회원_서비스_전체조회_테스트(){

        // given
        Member member = Member.builder().name("이찬복")
                .email("chanboklee@naver.com")
                .password("1234")
                .build();

        List<Member> members = new ArrayList<>();
        members.add(member);

        given(memberRepository.findAll()).willReturn(members);

        // when
        List<MemberListResponseDto> memberList = memberService.findMemberList();

        // then
        Assertions.assertEquals(members.size(), memberList.size());
        Assertions.assertEquals(members.get(0).getEmail(), memberList.get(0).getEmail());
        Assertions.assertEquals(members.get(0).getPassword(), memberList.get(0).getPassword());
    }
}