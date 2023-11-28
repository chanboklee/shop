package com.lee.shop.domain.delivery;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DeliveryStatus {

    READY("배송준비"),
    COMP("배송완료");

    private final String text;
}
