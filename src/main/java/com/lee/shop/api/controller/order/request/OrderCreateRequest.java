package com.lee.shop.api.controller.order.request;

import com.lee.shop.api.service.order.request.OrderCreateServiceRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class OrderCreateRequest {

    @NotEmpty(message = "주문 리스트는 필수입니다.")
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
