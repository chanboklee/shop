package com.lee.shop.api.controller.order.request;

import com.lee.shop.api.service.order.request.OrderCreateServiceRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequest {

    List<OrderProductRequest> orderProductRequests;

    public OrderCreateServiceRequest toServiceRequest() {
        return OrderCreateServiceRequest.builder()
                .orderProductRequests(orderProductRequests)
                .build();
    }
}
