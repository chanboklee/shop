package com.lee.shop.api.service.order.request;

import com.lee.shop.api.controller.order.request.OrderProductRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateServiceRequest {

    List<OrderProductRequest> orderProductRequests;

    @Builder
    private OrderCreateServiceRequest(List<OrderProductRequest> orderProductRequests) {
        this.orderProductRequests = orderProductRequests;
    }
}
