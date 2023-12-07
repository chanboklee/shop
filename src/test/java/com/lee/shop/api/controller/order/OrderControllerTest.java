package com.lee.shop.api.controller.order;

import com.lee.shop.ControllerTestSupport;
import com.lee.shop.api.controller.order.request.OrderCreateRequest;
import com.lee.shop.api.controller.order.request.OrderProductRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class OrderControllerTest extends ControllerTestSupport {

    @DisplayName("신규 주문을 등록한다.")
    @Test
    void saveOrder() throws Exception {
        // given
        List<OrderProductRequest> orderProductRequestList = List.of();
        OrderCreateRequest orderCreateRequest = OrderCreateRequest.builder()
                .orderProductRequests(orderProductRequestList)
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/members/{memberId}/orders", anyLong())
                        .content(objectMapper.writeValueAsString(orderCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data").isArray());
    }

    @DisplayName("신규 주문을 등록할 때 상품리스트는 1개 이상이어야 한다.")
    @Test
    void saveOrderWithEmptyOrderProducts() throws Exception {
        // given
        List<OrderProductRequest> orderProductRequestList = List.of();
        OrderCreateRequest orderCreateRequest = OrderCreateRequest.builder().orderProductRequests(orderProductRequestList).build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/members/{memberId}/orders", 1L)
                        .content(objectMapper.writeValueAsString(orderCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("주문 리스트는 필수입니다."))
                .andExpect(jsonPath("$.data").isEmpty());

    }
}