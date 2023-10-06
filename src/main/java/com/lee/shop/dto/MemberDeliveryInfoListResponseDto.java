package com.lee.shop.dto;

import com.lee.shop.domain.Member;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberDeliveryInfoListResponseDto {

    private String name;
    private String email;
    private List<DeliveryInfoListResponseDto> memberDeliveryInfos;

    public MemberDeliveryInfoListResponseDto(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.memberDeliveryInfos = member.getDeliveryInfos().stream().map(DeliveryInfoListResponseDto::new).collect(Collectors.toList());
    }
}
