package com.lee.shop.service;

import com.lee.shop.domain.Member;
import com.lee.shop.domain.MemberRepository;
import com.lee.shop.dto.MemberListResponseDto;
import com.lee.shop.dto.MemberResponseDto;
import com.lee.shop.dto.MemberSaveRequestDto;
import com.lee.shop.dto.MemberUpdateRequestDto;
import com.lee.shop.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    // private final DeliveryInfoRepository deliveryInfoRepository;

    @Transactional
    public Long save(Member member){
        validateExistMember(member.getEmail());
        memberRepository.save(member);
        return member.getId();
    }

    private void validateExistMember(String email){
        boolean isExistMember = memberRepository.existsByEmail(email);
        if(isExistMember){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public MemberResponseDto findMember(Long memberId){
        return memberRepository.findById(memberId)
                .map(MemberResponseDto::new)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));
    }

    @Transactional
    public Long updateMember(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        member.updateMemberInfo(memberUpdateRequestDto.getPassword());
        return member.getId();
    }

    public List<MemberListResponseDto> findMemberList(){
        return memberRepository.findAll().stream()
                .map(MemberListResponseDto::new)
                .collect(Collectors.toList());
    }

//    @Transactional
//    public Long addDeliveryInfo(Long memberId, MemberDeliveryInfoSaveRequestDto memberDeliveryInfoSaveRequestDto){
//        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));
//        DeliveryInfo deliveryInfo = DeliveryInfo.builder().recipient(memberDeliveryInfoSaveRequestDto.getRecipient())
//                .tel(memberDeliveryInfoSaveRequestDto.getTel())
//                .address(memberDeliveryInfoSaveRequestDto.getAddress())
//                .build();
//        deliveryInfo.addDeliveryInfo(member);
//        deliveryInfoRepository.save(deliveryInfo);
//        return deliveryInfo.getId();
//    }
//
//    @Transactional
//    public Long updateDeliveryInfo(Long memberId, Long deliveryInfoId, MemberDeliveryInfoUpdateDto memberDeliveryInfoUpdateDto){
//        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));
//        DeliveryInfo deliveryInfo = deliveryInfoRepository.findById(deliveryInfoId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 배송지 입니다."));
//        deliveryInfo.updateDeliveryInfo(memberDeliveryInfoUpdateDto.getRecipient(),
//                memberDeliveryInfoUpdateDto.getTel(),
//                memberDeliveryInfoUpdateDto.getAddress()
//        );
//        return deliveryInfo.getId();
//    }
}
