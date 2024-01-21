package com.lee.shop.api.service.member.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberUpdateServiceRequest {

    private String name;

    @Builder
    private MemberUpdateServiceRequest(String name) {
        this.name = name;
    }
}
