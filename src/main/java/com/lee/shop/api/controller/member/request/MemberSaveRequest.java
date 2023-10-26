package com.lee.shop.api.controller.member.request;

import com.lee.shop.api.service.member.request.MemberSaveServiceRequest;
import lombok.Getter;

@Getter
public class MemberSaveRequest {

    private String name;
    private String email;
    private String password;

    public MemberSaveServiceRequest toServiceRequest(){
        return MemberSaveServiceRequest.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
