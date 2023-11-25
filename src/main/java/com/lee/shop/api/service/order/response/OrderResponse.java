package com.lee.shop.api.service.order.response;

import com.lee.shop.domain.order.Order;
import com.lee.shop.domain.order.OrderStatus;
import com.lee.shop.domain.orderproduct.OrderProduct;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponse {

    private Long memberId;
    private List<OrderProduct> orderProducts;
    private OrderStatus orderStatus;

    @Builder
    private OrderResponse(Long memberId, List<OrderProduct> orderProducts, OrderStatus orderStatus) {
        this.memberId = memberId;
        this.orderProducts = orderProducts;
        this.orderStatus = orderStatus;
    }

    public static OrderResponse of(Order order){
        return OrderResponse.builder()
                .memberId(order.getMember().getId())
                .orderProducts(order.getOrderProducts())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}
