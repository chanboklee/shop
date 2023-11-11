package com.lee.shop.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.lee.shop.domain.product.ProductType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어온다.")
    @Test
    void findTop1ByOrderByIdDesc(){
        // given
        String targetProductNumber = "003";

        Product product1 = createProduct("맨투맨", "001", 100, 16000, TOP);
        Product product2 = createProduct("청바지", "002", 50, 30000, BOTTOM);
        Product product3 = createProduct("귀걸이", "003", 10, 200000, ACCESSORIES);
        
        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        String latestProductNumber = productRepository.findByProductNumberTop1ByOrderByIdDesc();

        // then
        assertThat(latestProductNumber).isEqualTo(targetProductNumber);
    }

    @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어올 때, 상품이 하나도 없는 경우에는 null을 반환한다.")
    @Test
    void findLatestProductNumberWhenProductIsEmpty(){

        // when
        String latestProductNumber = productRepository.findByProductNumberTop1ByOrderByIdDesc();

        // then
        assertThat(latestProductNumber).isNull();
    }

    private Product createProduct(String name, String productNumber, int stockQuantity, int price, ProductType productType) {
        return Product.builder()
                .name(name)
                .productNumber(productNumber)
                .stockQuantity(stockQuantity)
                .price(price)
                .productType(productType)
                .build();
    }
}