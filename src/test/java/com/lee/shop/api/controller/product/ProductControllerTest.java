package com.lee.shop.api.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.shop.api.controller.product.request.ProductCreateRequest;
import com.lee.shop.api.service.product.ProductService;
import com.lee.shop.domain.product.ProductSellingStatus;
import com.lee.shop.domain.product.ProductType;
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

import static com.lee.shop.domain.product.ProductSellingStatus.SELLING;
import static com.lee.shop.domain.product.ProductType.TOP;

@WebMvcTest(ProductController.class)
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
        ProductCreateRequest productCreateRequest = createProductRequest("맨투맨", 12000, 20, TOP, SELLING);

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .content(objectMapper.writeValueAsString(productCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    private ProductCreateRequest createProductRequest(String name, int price, int stockQuantity, ProductType productType, ProductSellingStatus productSellingStatus) {
        return ProductCreateRequest.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .productType(productType)
                .productSellingStatus(productSellingStatus)
                .build();
    }
}