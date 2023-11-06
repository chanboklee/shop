package com.lee.shop.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductType {

    TOP("상의"),
    BOTTOM("하의"),
    ACCESSORIES("악세서리");

    private final String text;
}
