package com.lee.shop.api.service.product;

import com.lee.shop.api.service.product.request.ProductCreateServiceRequest;
import com.lee.shop.api.service.product.response.ProductResponse;
import com.lee.shop.domain.product.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.lee.shop.domain.product.ProductType.TOP;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @DisplayName("상품명, 가격, 재고수, 상품타입을 입력받아 상품을 등록한다.")
    @Test
    void createProduct(){
        ProductCreateServiceRequest productCreateServiceRequest = ProductCreateServiceRequest.builder()
                .name("나이키 맨투맨")
                .price(16000)
                .stockQuantity(100)
                .productType(TOP)
                .build();

        ProductResponse productResponse = productService.createProduct(productCreateServiceRequest);

        assertThat(productResponse.getId()).isNotNull();
        assertThat(productResponse)
                .extracting("name", "price", "stockQuantity", "productType")
                .contains("맨투맨", 16000, 100, TOP);
    }
}