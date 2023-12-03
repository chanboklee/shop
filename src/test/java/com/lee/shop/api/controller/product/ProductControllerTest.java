package com.lee.shop.api.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.shop.api.controller.product.request.ProductCreateRequest;
import com.lee.shop.api.service.product.ProductService;
import com.lee.shop.api.service.product.response.ProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.lee.shop.domain.product.ProductSellingStatus.SELLING;
import static com.lee.shop.domain.product.ProductType.BOTTOM;
import static com.lee.shop.domain.product.ProductType.TOP;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @DisplayName("상품명, 가격, 재고, 상품타입, 상품판매상태를 입력받아 상품을 등록한다.")
    @Test
    void createProduct() throws Exception {

        // given
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .name("맨투맨")
                .price(12000)
                .stockQuantity(20)
                .productType(TOP)
                .productSellingStatus(SELLING)
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .content(objectMapper.writeValueAsString(productCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @DisplayName("신규 상품을 등록할 때는 상품명이 필수값이다.")
    @Test
    void createProductWithoutName() throws Exception {
        // given
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .price(12000)
                .stockQuantity(20)
                .productType(TOP)
                .productSellingStatus(SELLING)
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .content(objectMapper.writeValueAsString(productCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("상품명은 필수입니다."));
    }

    @DisplayName("신규 상품을 등록할 때 가격은 0보다 커야한다.")
    @Test
    void createProductGreaterThanZeroPrice() throws Exception {
        // given
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .name("맨투맨")
                .stockQuantity(20)
                .productType(TOP)
                .productSellingStatus(SELLING)
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .content(objectMapper.writeValueAsString(productCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("상품 가격은 양수여야 합니다."));
    }

    @DisplayName("신규 상품을 등록할 때 재고는 0보다 커야한다.")
    @Test
    void createProductGreaterThanZeroStockQuantity() throws Exception {
        // given
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .name("맨투맨")
                .price(12000)
                .productType(TOP)
                .productSellingStatus(SELLING)
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .content(objectMapper.writeValueAsString(productCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("재고는 양수여야 합니다."));
    }

    @DisplayName("신규 상품을 등록할 때 상품타입은 필수값이다.")
    @Test
    void createProductWithoutProductType() throws Exception {
        // given
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .name("맨투맨")
                .price(12000)
                .stockQuantity(20)
                .productSellingStatus(SELLING)
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .content(objectMapper.writeValueAsString(productCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("상품 타입은 필수입니다."));
    }

    @DisplayName("신규 상품을 등록할 때 상품 판매상태는 필수값이다.")
    @Test
    void createProductWithoutProductSellingStatus() throws Exception {
        // given
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .name("맨투맨")
                .price(12000)
                .stockQuantity(20)
                .productType(TOP)
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .content(objectMapper.writeValueAsString(productCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("상품 판매상태는 필수입니다."));
    }

    @DisplayName("판매 상품을 조회한다.")
    @Test
    void getSellingProducts() throws Exception {
        // given
        ProductResponse productResponse1 = ProductResponse.builder()
                .productNumber("001")
                .name("맨투맨")
                .price(12000)
                .stockQuantity(20)
                .productType(TOP)
                .productSellingStatus(SELLING)
                .build();

        ProductResponse productResponse2 = ProductResponse.builder()
                .productNumber("002")
                .name("청바지")
                .price(52000)
                .stockQuantity(50)
                .productType(BOTTOM)
                .productSellingStatus(SELLING)
                .build();

        List<ProductResponse> productResponseList = List.of(productResponse1, productResponse2);
        when(productService.getSellingProducts()).thenReturn(productResponseList);

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data[0].productNumber").value("001"))
                .andExpect(jsonPath("$.data[0].name").value("맨투맨"))
                .andExpect(jsonPath("$.data[0].price").value(12000))
                .andExpect(jsonPath("$.data[0].stockQuantity").value(20))
                .andExpect(jsonPath("$.data[0].productType").value("TOP"))
                .andExpect(jsonPath("$.data[0].productSellingStatus").value("SELLING"))
                .andExpect(jsonPath("$.data[1].productNumber").value("002"))
                .andExpect(jsonPath("$.data[1].name").value("청바지"))
                .andExpect(jsonPath("$.data[1].price").value(52000))
                .andExpect(jsonPath("$.data[1].stockQuantity").value(50))
                .andExpect(jsonPath("$.data[1].productType").value("BOTTOM"))
                .andExpect(jsonPath("$.data[1].productSellingStatus").value("SELLING"))
                .andExpect(jsonPath("$.data").isArray());
    }
}