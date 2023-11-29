package com.lee.shop.api.controller.order.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProductRequest {

    private String productNumber;
    private int price;
    private int quantity;

    @Builder
    private OrderProductRequest(String productNumber, int price, int quantity) {
        this.productNumber = productNumber;
        this.price = price;
        this.quantity = quantity;
    }
}
