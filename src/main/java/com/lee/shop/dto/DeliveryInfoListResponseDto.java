package com.lee.shop.dto;

import com.lee.shop.domain.Address;
import com.lee.shop.domain.DeliveryInfo;
import lombok.Data;

@Data
public class DeliveryInfoListResponseDto {

    private String recipient;
    private String tel;
    private Address address;

    public DeliveryInfoListResponseDto(DeliveryInfo deliveryInfo) {
        this.recipient = deliveryInfo.getRecipient();
        this.tel = deliveryInfo.getTel();
        this.address = deliveryInfo.getAddress();
    }
}
