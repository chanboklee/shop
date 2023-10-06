package com.lee.shop.dto;

import com.lee.shop.domain.Address;
import com.lee.shop.domain.DeliveryInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DeliveryInfoListResponseDto {

    @Schema(description = "수령자", example = "이찬복")
    private String recipient;
    @Schema(description = "전화번호", example = "01012344321")
    private String tel;
    @Schema(description = "주소")
    private Address address;

    public DeliveryInfoListResponseDto(DeliveryInfo deliveryInfo) {
        this.recipient = deliveryInfo.getRecipient();
        this.tel = deliveryInfo.getTel();
        this.address = deliveryInfo.getAddress();
    }
}
