package com.lee.shop.domain.order;

import com.lee.shop.domain.BaseEntity;
import com.lee.shop.domain.delivery.Delivery;
import com.lee.shop.domain.member.Member;
import com.lee.shop.domain.orderproduct.OrderProduct;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(Member member, List<OrderProduct> orderProducts, OrderStatus orderStatus) {
        this.member = member;
        this.orderProducts = orderProducts.stream()
                .map(orderProduct -> new OrderProduct(this))
                .collect(Collectors.toList());
        this.orderStatus = orderStatus;
    }

    public static Order createOrder(Member member, List<OrderProduct> orderProducts) {
        return Order.builder()
                .member(member)
                .orderProducts(orderProducts)
                .orderStatus(ORDER)
                .build();
    }
}
