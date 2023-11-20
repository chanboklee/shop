package com.lee.shop.api.controller.member;

import com.lee.shop.api.ApiResponse;
import com.lee.shop.api.controller.member.request.MemberSaveRequest;
import com.lee.shop.api.service.member.MemberService;
import com.lee.shop.api.service.member.response.MemberResponse;
import com.lee.shop.api.service.member.response.MemberSaveResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "MemberApiController", description = "회원 API")
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ApiResponse<MemberSaveResponse> saveMember(@Valid @RequestBody MemberSaveRequest memberSaveRequest){
        return ApiResponse.ok(memberService.saveMember(memberSaveRequest.toServiceRequest()));
    }

    @GetMapping("/members")
    public ApiResponse<List<MemberResponse>> findMembers(){
        return ApiResponse.ok(memberService.findMembers());
    }

    @GetMapping("/members/{memberId}")
    public ApiResponse<MemberResponse> findMember(@PathVariable("memberId") Long memberId){
        return ApiResponse.ok(memberService.findMember(memberId));
    }
}
