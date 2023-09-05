package com.lee.shop.dto;

import com.lee.shop.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberResponseDto {

    @Schema(description = "이메일", nullable = false, example = "test@naver.com")
    private String email;

    @Schema(description = "비밀번호", nullable = false, example = "1234")
    private String password;

    public MemberResponseDto(Member member){
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
