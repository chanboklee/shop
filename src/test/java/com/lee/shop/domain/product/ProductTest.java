package com.lee.shop.domain.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.lee.shop.domain.product.ProductType.TOP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @DisplayName("상품의 재고가 감소한다.")
    @Test
    void removeStock(){
        // given
        Product product = createProduct("001", "나이키 맨투맨", 16000, 100, TOP);
        Product savedProduct = productRepository.save(product);

        // when
        savedProduct.removeStock(50);

        // then
        assertThat(savedProduct.getStockQuantity()).isEqualTo(50);
    }

    @DisplayName("상품의 재고가 0개 미만이면 예외가 발생한다.")
    @Test
    void removeStockLessThanOneThrowException(){
        // given
        Product product = createProduct("001", "나이키 맨투맨", 16000, 100, TOP);
        Product savedProduct = productRepository.save(product);

        // when // then
        assertThatThrownBy(() -> savedProduct.removeStock(101))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("재고가 부족합니다.");
    }

    private Product createProduct(String productNumber, String name, int price, int stockQuantity, ProductType productType) {
        return Product.builder()
                .productNumber(productNumber)
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .productType(productType)
                .build();
    }
}