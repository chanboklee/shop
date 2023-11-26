package com.lee.shop.api.service.order.response;

import com.lee.shop.domain.order.Order;
import com.lee.shop.domain.order.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponse {

    private String name;
    private Long orderId;

    private List<OrderProductResponse> orderProducts;
    private OrderStatus orderStatus;

    @Builder
    private OrderResponse(String name, Long orderId, List<OrderProductResponse> orderProducts, OrderStatus orderStatus) {
        this.name = name;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
    }

    public static OrderResponse of(Order order){
        return OrderResponse.builder()
                .name(order.getMember().getName())
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .orderProducts(order.getOrderProducts().stream()
                        .map(orderProduct -> OrderProductResponse.of(orderProduct))
                        .collect(Collectors.toList())
                )
                .build();
    }
}
