package com.lee.shop.api.controller.order;

import com.lee.shop.api.ApiResponse;
import com.lee.shop.api.controller.order.request.OrderCreateRequest;
import com.lee.shop.api.service.order.OrderService;
import com.lee.shop.api.service.order.response.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/members/{memberId}/orders")
    public ApiResponse<OrderResponse> saveOrder(@PathVariable("memberId") Long memberId, @Valid @RequestBody OrderCreateRequest orderCreateRequest){
        return ApiResponse.ok(orderService.saveOrder(memberId, orderCreateRequest.toServiceRequest()));
    }
}
