package com.lee.shop.api.service.product;

import com.lee.shop.IntegrationTestSupport;
import com.lee.shop.api.service.product.request.ProductCreateServiceRequest;
import com.lee.shop.api.service.product.response.ProductResponse;
import com.lee.shop.domain.product.Product;
import com.lee.shop.domain.product.ProductRepository;
import com.lee.shop.domain.product.ProductSellingStatus;
import com.lee.shop.domain.product.ProductType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.lee.shop.domain.product.ProductSellingStatus.*;
import static com.lee.shop.domain.product.ProductType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class ProductServiceTest extends IntegrationTestSupport {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001이다.")
    @Test
    void createProductByProductNumber(){
        // given
        ProductCreateServiceRequest productCreateServiceRequest = ProductCreateServiceRequest.builder()
                .name("나이키 맨투맨")
                .price(16000)
                .stockQuantity(100)
                .productType(TOP)
                .productSellingStatus(SELLING)
                .build();

        // when
        ProductResponse productResponse = productService.createProduct(productCreateServiceRequest);

        // then
        assertThat(productResponse)
                .extracting("productNumber", "name", "price", "stockQuantity", "productType", "productSellingStatus")
                .contains("001", "나이키 맨투맨", 16000, 100, TOP, SELLING);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1)
                .extracting("productNumber", "name", "price", "stockQuantity", "productType", "productSellingStatus")
                .containsExactly(
                        tuple("001", "나이키 맨투맨", 16000, 100, TOP, SELLING)
                );
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
    
    @DisplayName("상품상태가 판매중, 판매보류인 상품 리스트만 조회한다.")
    @Test
    void getSellingProducts(){
        // given
        Product product1 = saveProduct("001", "나이키 맨투맨", 25000, 100, TOP, SELLING);
        Product product3 = saveProduct("002", "귀걸이", 40000, 20, ACCESSORIES, STOP_SELLING);
        Product product4 = saveProduct("003", "아디다스 패딩", 120000, 82, ACCESSORIES, HOLD);
        Product product2 = saveProduct("004", "청바지", 12000, 50, BOTTOM, SELLING);


        productRepository.saveAll(List.of(product1, product2, product3, product4));

        // when
        List<Product> products = productRepository.findAllByProductSellingStatusIn(forDisplay());

        // then
        assertThat(products)
                .extracting("productNumber", "name", "price", "stockQuantity", "productType", "productSellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001", "나이키 맨투맨", 25000, 100, TOP, SELLING),
                        tuple("004", "청바지", 12000, 50, BOTTOM, SELLING),
                        tuple("003", "아디다스 패딩", 120000, 82, ACCESSORIES, HOLD)
                );
    }

    private Product saveProduct(String productNumber, String name, int price, int stockQuantity, ProductType productType, ProductSellingStatus productSellingStatus) {
        return Product.builder()
                .productNumber(productNumber)
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .productType(productType)
                .productSellingStatus(productSellingStatus)
                .build();
    }
}