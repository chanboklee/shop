package com.lee.shop.dto;

import com.lee.shop.domain.Member;
import lombok.Data;

@Data
public class MemberListResponseDto {

    private String email;
    private String password;

    public MemberListResponseDto(Member member){
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
