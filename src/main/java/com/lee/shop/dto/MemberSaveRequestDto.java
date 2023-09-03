package com.lee.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberSaveRequestDto {

    @NotNull(message = "이메일을 입력해주세요.")
    @Email
    private String email;

    @NotNull(message = "패스워드를 입력해주세요.")
    private String password;
}
