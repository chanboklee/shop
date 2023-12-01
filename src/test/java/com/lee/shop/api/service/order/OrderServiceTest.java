package com.lee.shop.api.service.order;

import com.lee.shop.api.controller.order.request.OrderProductRequest;
import com.lee.shop.api.service.order.request.OrderCreateServiceRequest;
import com.lee.shop.api.service.order.response.OrderResponse;
import com.lee.shop.domain.member.Member;
import com.lee.shop.domain.member.MemberRepository;
import com.lee.shop.domain.order.OrderRepository;
import com.lee.shop.domain.orderproduct.OrderProductRepository;
import com.lee.shop.domain.product.Product;
import com.lee.shop.domain.product.ProductRepository;
import com.lee.shop.domain.product.ProductSellingStatus;
import com.lee.shop.domain.product.ProductType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.lee.shop.domain.product.ProductSellingStatus.SELLING;
import static com.lee.shop.domain.product.ProductSellingStatus.STOP_SELLING;
import static com.lee.shop.domain.product.ProductType.ACCESSORIES;
import static com.lee.shop.domain.product.ProductType.TOP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ActiveProfiles("test")
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderService orderService;

    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
    }

    @DisplayName("재고와 관련된 상품이 포함되어 있는 주문번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrder(){
        // given
        Member member = createMember("이찬복", "chanboklee@naver.com", "1234");
        memberRepository.save(member);

        Product product1 = createProduct("001", "나이키 맨투맨", 25000, 100, TOP, SELLING);
        Product product2 = createProduct("002", "귀걸이", 40000, 20, ACCESSORIES, STOP_SELLING);
        productRepository.saveAll(List.of(product1, product2));

        OrderProductRequest orderProductRequest1 = OrderProductRequest.builder()
                .productNumber("001")
                .price(12000)
                .quantity(50)
                .build();

        OrderProductRequest orderProductRequest2 = OrderProductRequest.builder()
                .productNumber("002")
                .price(52000)
                .quantity(10)
                .build();

        List<OrderProductRequest> orderProductRequestList = List.of(orderProductRequest1, orderProductRequest2);

        OrderCreateServiceRequest orderCreateServiceRequest = OrderCreateServiceRequest.builder()
                .orderProductRequests(orderProductRequestList)
                .build();

        // when
        OrderResponse orderResponse = orderService.saveOrder(member.getId(), orderCreateServiceRequest);

        // then
        assertThat(orderResponse)
                .extracting("name", "orderId")
                .contains("이찬복", 1L);
    }

    @DisplayName("주문한 수량이 상품의 재고보다 많으면 예외가 발생한다.")
    @Test
    void createOrderWithoutStockQuantityThrowException(){
        // given
        Member member = createMember("이찬복", "chanboklee@naver.com", "1234");
        memberRepository.save(member);

        Product product1 = createProduct("001", "나이키 맨투맨", 25000, 100, TOP, SELLING);
        Product product2 = createProduct("002", "귀걸이", 40000, 20, ACCESSORIES, STOP_SELLING);
        productRepository.saveAll(List.of(product1, product2));

        // when
        OrderProductRequest orderProductRequest = OrderProductRequest.builder()
                .productNumber("001")
                .price(25000)
                .quantity(101)
                .build();

        List<OrderProductRequest> orderProductRequestList = List.of(orderProductRequest);

        OrderCreateServiceRequest orderCreateServiceRequest = OrderCreateServiceRequest.builder()
                .orderProductRequests(orderProductRequestList)
                .build();

        // then
        assertThatThrownBy(() -> orderService.saveOrder(member.getId(), orderCreateServiceRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("재고가 부족합니다.");
    }

    private Member createMember(String name, String email, String password) {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    private Product createProduct(String productNumber, String name, int price, int stockQuantity, ProductType productType, ProductSellingStatus productSellingStatus) {
        return Product.builder()
                .productNumber(productNumber)
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .productType(productType)
                .build();
    }
}