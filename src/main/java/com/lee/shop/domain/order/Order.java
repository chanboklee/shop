package com.lee.shop.domain.order;

import com.lee.shop.domain.BaseEntity;
import com.lee.shop.domain.member.Member;
import com.lee.shop.domain.orderproduct.OrderProduct;
import com.lee.shop.domain.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.lee.shop.domain.order.OrderStatus.ORDER;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    private Order(Member member, List<Product> products, OrderStatus orderStatus) {
        this.member = member;
        this.orderProducts = products.stream()
                .map(product -> new OrderProduct(this, product))
                .collect(Collectors.toList());
        this.orderStatus = orderStatus;
    }

    public static Order createOrder(List<Product> products){
        return Order.builder()
                .orderStatus(ORDER)
                .products(products)
                .build();
    }
}
