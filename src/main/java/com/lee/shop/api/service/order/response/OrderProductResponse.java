package com.lee.shop.api.service.order.response;

import com.lee.shop.domain.orderproduct.OrderProduct;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProductResponse {

    private String productName;
    private int orderPrice;
    private int count;

    @Builder
    private OrderProductResponse(String productName, int orderPrice, int count) {
        this.productName = productName;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderProductResponse of(OrderProduct orderProduct){
        return OrderProductResponse.builder()
                .productName(orderProduct.getProduct().getName())
                .orderPrice(orderProduct.getPrice())
                .count(orderProduct.getQuantity())
                .build();
    }
}
