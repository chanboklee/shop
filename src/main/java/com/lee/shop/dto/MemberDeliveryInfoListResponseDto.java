package com.lee.shop.dto;

import com.lee.shop.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberDeliveryInfoListResponseDto {

    @Schema(description = "성명", example = "이찬복")
    private String name;
    @Schema(description = "이메일", example = "chanboklee@naver.com")
    private String email;
    @Schema(description = "배송지 리스트")
    private List<DeliveryInfoListResponseDto> memberDeliveryInfos;

    public MemberDeliveryInfoListResponseDto(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.memberDeliveryInfos = member.getDeliveryInfos().stream().map(DeliveryInfoListResponseDto::new).collect(Collectors.toList());
    }
}
