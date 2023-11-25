package com.lee.shop.api.controller.order.request;

import lombok.Getter;

@Getter
public class OrderProductRequest {

    private String productNumber;
    private int price;
    private int quantity;

}
