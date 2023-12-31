package com.lee.shop.api.service.member.response;

import com.lee.shop.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;
    private String name;
    private String email;
    private String password;

    @Builder
    private MemberResponse(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static MemberResponse of(Member member){
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .build();
    }
}
