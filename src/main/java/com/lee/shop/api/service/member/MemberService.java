package com.lee.shop.api.service.member;

import com.lee.shop.api.service.member.request.MemberSaveServiceRequest;
import com.lee.shop.api.service.member.response.MemberResponse;
import com.lee.shop.api.service.member.response.MemberSaveResponse;
import com.lee.shop.domain.member.Member;
import com.lee.shop.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberSaveResponse saveMember(MemberSaveServiceRequest memberSaveServiceRequest){
        Member member = Member.builder()
                .name(memberSaveServiceRequest.getName())
                .email(memberSaveServiceRequest.getEmail())
                .password(memberSaveServiceRequest.getPassword())
                .build();

        validateDuplicateMember(member);

        Member savedMember = memberRepository.save(member);
        return MemberSaveResponse.of(savedMember);
    }

    private void validateDuplicateMember(Member member) {
        boolean isExistsEmail = memberRepository.existsByEmail(member.getEmail());
        if(isExistsEmail){
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    public List<MemberResponse> findMembers(){
        return memberRepository.findAll().stream()
                .map(member -> MemberResponse.of(member))
                .collect(Collectors.toList());
    }

    public MemberResponse findMember(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return MemberResponse.of(member);
    }
}
