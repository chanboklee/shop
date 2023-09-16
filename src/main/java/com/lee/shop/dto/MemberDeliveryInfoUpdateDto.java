package com.lee.shop.dto;

import com.lee.shop.domain.Address;
import lombok.Data;

@Data
public class MemberDeliveryInfoUpdateDto {

    private String recipient;
    private String tel;
    private Address address;

}
