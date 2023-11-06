package com.lee.shop.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    ORDER("주문"),
    CANCEL("주문취소");

    private final String text;

}
