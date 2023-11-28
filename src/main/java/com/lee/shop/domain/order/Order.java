package com.lee.shop.domain.order;

import com.lee.shop.domain.BaseEntity;
import com.lee.shop.domain.delivery.Delivery;
import com.lee.shop.domain.delivery.DeliveryStatus;
import com.lee.shop.domain.member.Member;
import com.lee.shop.domain.orderproduct.OrderProduct;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.lee.shop.domain.delivery.DeliveryStatus.COMP;
import static com.lee.shop.domain.order.OrderStatus.CANCEL;
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
        this.orderProducts = orderProducts;
        this.orderStatus = orderStatus;
    }

    public static Order createOrder(Member member, List<OrderProduct> orderProducts) {
        return Order.builder()
                .member(member)
                .orderProducts(orderProducts)
                .orderStatus(ORDER)
                .build();
    }

    public void cancel(){
        if(delivery.getDeliveryStatus() == COMP){
            throw new IllegalArgumentException("배송완료된 상품은 취소가 불가능합니다.");
        }
        this.orderStatus = CANCEL;
        for(OrderProduct orderProduct : orderProducts){
            orderProduct.cancel();
        }
    }
}
