package com.lee.shop.api.controller.member.request;

import com.lee.shop.api.service.member.request.MemberUpdateServiceRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberUpdateRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    public MemberUpdateServiceRequest toServiceRequest() {
        return MemberUpdateServiceRequest.builder()
                .name(name)
                .build();
    }
}
