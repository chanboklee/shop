package com.lee.shop.service;

import com.lee.shop.domain.Member;
import com.lee.shop.domain.MemberRepository;
import com.lee.shop.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberSaveRequestDto memberSaveRequestDto){
        Member member = Member.builder().email(memberSaveRequestDto.getEmail())
                .password(memberSaveRequestDto.getPassword())
                .build();

        validateExistMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateExistMember(Member member){
        boolean isExistMember = memberRepository.existsByEmail(member.getEmail());
        if(isExistMember){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
