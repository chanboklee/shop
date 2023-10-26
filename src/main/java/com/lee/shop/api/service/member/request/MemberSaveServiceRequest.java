package com.lee.shop.api.service.member.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSaveServiceRequest {

    private String name;
    private String email;
    private String password;

    @Builder
    public MemberSaveServiceRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
