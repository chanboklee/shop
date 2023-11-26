package com.lee.shop.domain.orderproduct;

import com.lee.shop.domain.BaseEntity;
import com.lee.shop.domain.order.Order;
import com.lee.shop.domain.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OrderProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long id;

    private int price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 연관관계 메서드
     * @param order
     */
    public OrderProduct(Order order){
        this.order = order;
    }

    @Builder
    private OrderProduct(int price, int quantity, Order order, Product product) {
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public static OrderProduct createOrderProduct(int price, int quantity, Product product){
        OrderProduct orderProduct = OrderProduct.builder()
                .price(price)
                .quantity(quantity)
                .product(product)
                .build();

        product.removeStock(quantity);
        return orderProduct;
    }

    public int getTotalPrice(){
        return price * quantity;
    }

    public void cancel(){
        getProduct().addStock(quantity);
    }
}
