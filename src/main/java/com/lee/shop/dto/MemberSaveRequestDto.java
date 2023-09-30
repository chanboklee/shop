package com.lee.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberSaveRequestDto {

    @NotNull(message = "성명을 입력해주세요.")
    @Schema(description = "성명", nullable = false, example = "이찬복")
    private String name;

    @NotNull(message = "이메일을 입력해주세요.")
    @Email
    @Schema(description = "이메일", nullable = false, example = "test@naver.com")
    private String email;

    @NotNull(message = "패스워드를 입력해주세요.")
    @Schema(description = "패스워드", nullable = false, example = "1234")
    private String password;

}
