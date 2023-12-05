package com.lee.shop.api.controller.order.request;

import com.lee.shop.api.service.order.request.OrderCreateServiceRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequest {

    List<OrderProductRequest> orderProductRequests;

    @Builder
    private OrderCreateRequest(List<OrderProductRequest> orderProductRequests) {
        this.orderProductRequests = orderProductRequests;
    }

    public OrderCreateServiceRequest toServiceRequest() {
        return OrderCreateServiceRequest.builder()
                .orderProductRequests(orderProductRequests)
                .build();
    }
}
