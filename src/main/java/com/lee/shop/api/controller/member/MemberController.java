package com.lee.shop.api.controller.member;

import com.lee.shop.api.controller.member.request.MemberSaveRequest;
import com.lee.shop.api.service.member.MemberService;
import com.lee.shop.api.service.member.response.MemberListResponse;
import com.lee.shop.api.service.member.response.MemberSaveResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "회원 가입", description = "회원 가입",
            responses = {@ApiResponse(content = @Content(schema = @Schema(implementation = MemberSaveResponse.class)))}
    )
    @PostMapping("/members")
    public MemberSaveResponse saveMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest){
        return memberService.saveMember(memberSaveRequest.toServiceRequest());
    }

    @GetMapping("/members")
    public List<MemberListResponse> findMembers(){
        return memberService.findMembers();
    }
}
