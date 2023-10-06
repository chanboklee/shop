package com.lee.shop.controller.api;

import com.lee.shop.domain.Member;
import com.lee.shop.dto.*;
import com.lee.shop.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "회원 가입", description = "회원 가입",
            responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = Long.class)))}
    )
    @PostMapping("/members")
    public ResponseEntity<Long> joinMember(@RequestBody @Valid MemberSaveRequestDto memberSaveRequestDto){
        Member member = Member.builder().name(memberSaveRequestDto.getName())
                .email(memberSaveRequestDto.getEmail())
                .password(memberSaveRequestDto.getPassword())
                .build();
        Long memberId = memberService.save(member);
        return ResponseEntity.ok().body(memberId);
    }

    @Operation(summary = "회원 조회", description = "회원 단건 조회",
            responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))}
    )
    @GetMapping("/members/{id}")
    public MemberResponseDto searchMember(@PathVariable("id") Long id){
        return memberService.findMember(id);
    }

    @Operation(summary = "회원 조회", description = "회원 전체 조회",
        responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = MemberListResponseDto.class)))}
    )
    @GetMapping("/members")
    public ResponseEntity<List<MemberListResponseDto>> searchMemberList(){
        List<MemberListResponseDto> memberList = memberService.findMemberList();
        return ResponseEntity.ok().body(memberList);
    }

    @Operation(summary = "회원 수정", description = "회원 정보 수정",
        responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = Long.class)))}
    )
    @PutMapping("/members/{id}")
    public ResponseEntity<Long> updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberUpdateRequestDto memberUpdateRequestDto){
        Long memberId = memberService.updateMember(id, memberUpdateRequestDto);
        return ResponseEntity.ok().body(memberId);
    }

    @Operation(summary = "배송지 저장", description = "회원 배송지 등록",
        responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = Long.class)))}
    )
    @PostMapping("/members/{id}/delivery_infos")
    public ResponseEntity<Long> addDeliveryInfo(@PathVariable("id") Long id, @RequestBody @Valid MemberDeliveryInfoSaveRequestDto memberDeliveryInfoSaveRequestDto){
        Long deliveryInfoId = memberService.addDeliveryInfo(id, memberDeliveryInfoSaveRequestDto);
        return ResponseEntity.ok().body(deliveryInfoId);
    }

    @Operation(summary = "배송지 수정", description = "회원 배송지 수정",
        responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = Long.class)))}
    )
    @PutMapping("/members/{memberId}/delivery_infos/{deliveryInfoId}")
    public ResponseEntity<Long> updateDeliveryInfo(@PathVariable("memberId") Long memberId,
                                                   @PathVariable("deliveryInfoId") Long deliveryInfoId,
                                                   @RequestBody @Valid MemberDeliveryInfoUpdateDto memberDeliveryInfoUpdateDto){

        Long id = memberService.updateDeliveryInfo(memberId, deliveryInfoId, memberDeliveryInfoUpdateDto);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/members/{memberId}/delivery_infos")
    public ResponseEntity<List<MemberDeliveryInfoListResponseDto>> findMemberDeliveryInfos(@PathVariable("memberId") Long memberId){
        List<MemberDeliveryInfoListResponseDto> memberDeliveryInfos = memberService.findMemberDeliveryInfos(memberId);
        return ResponseEntity.ok().body(memberDeliveryInfos);
    }
}
