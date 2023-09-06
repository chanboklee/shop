package com.lee.shop.controller.api;

import com.lee.shop.dto.MemberResponseDto;
import com.lee.shop.dto.MemberSaveRequestDto;
import com.lee.shop.dto.MemberUpdateRequestDto;
import com.lee.shop.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
