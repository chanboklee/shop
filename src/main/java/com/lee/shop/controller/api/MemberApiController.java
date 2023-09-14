package com.lee.shop.controller.api;

import com.lee.shop.dto.*;
import com.lee.shop.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "MemberApiController", description = "회원 API")
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @Operation(summary = "회원 저장", description = "회원 가입")
    @PostMapping("/members")
    public ResponseEntity<Long> joinMember(@RequestBody @Valid MemberSaveRequestDto memberSaveRequestDto){
        Long memberId = memberService.save(memberSaveRequestDto);
        return ResponseEntity.ok().body(memberId);
    }

    @Operation(summary = "회원 조회", description = "회원 단건 조회")
    @GetMapping("/members/{id}")
    public MemberResponseDto searchMember(@PathVariable("id") Long id){
        return memberService.findMember(id);
    }

    @Operation(summary = "회원 조회", description = "회원 전체 조회")
    @GetMapping("/members")
    public ResponseEntity<List<MemberListResponseDto>> searchMemberList(){
        List<MemberListResponseDto> memberList = memberService.findMemberList();
        return ResponseEntity.ok().body(memberList);
    }

    @Operation(summary = "회원 수정", description = "회원 정보 수정")
    @PutMapping("/members/{id}")
    public ResponseEntity<Long> updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberUpdateRequestDto memberUpdateRequestDto){
        Long memberId = memberService.updateMember(id, memberUpdateRequestDto);
        return ResponseEntity.ok().body(memberId);
    }

    @Operation(summary = "배송지 저장", description = "회원 배송지를 등록")
    @PostMapping("/members/{id}/delivery_infos")
    public ResponseEntity<Long> addDeliveryInfo(@PathVariable("id") Long id, @RequestBody @Valid MemberDeliveryInfoSaveRequestDto memberDeliveryInfoSaveRequestDto){
        Long deliveryInfoId = memberService.addDeliveryInfo(id, memberDeliveryInfoSaveRequestDto);
        return ResponseEntity.ok().body(deliveryInfoId);
    }
}
