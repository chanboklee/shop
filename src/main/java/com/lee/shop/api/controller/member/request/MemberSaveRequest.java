package com.lee.shop.api.controller.member.request;

import com.lee.shop.api.service.member.request.MemberSaveServiceRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberSaveRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @NotBlank(message = "패스워드는 필수입니다.")
    private String password;

    @Builder
    private MemberSaveRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public MemberSaveServiceRequest toServiceRequest(){
        return MemberSaveServiceRequest.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
