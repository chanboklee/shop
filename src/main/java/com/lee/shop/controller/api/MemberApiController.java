package com.lee.shop.controller.api;

import com.lee.shop.dto.MemberSaveRequestDto;
import com.lee.shop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Long> joinMember(@RequestBody @Valid MemberSaveRequestDto memberSaveRequestDto){
        Long memberId = memberService.save(memberSaveRequestDto);
        return ResponseEntity.ok().body(memberId);
    }
}
