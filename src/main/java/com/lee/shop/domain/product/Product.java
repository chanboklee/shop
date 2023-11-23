package com.lee.shop.domain.product;

import com.lee.shop.domain.BaseEntity;
import com.lee.shop.domain.orderproduct.OrderProduct;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;
    private String productNumber;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    private ProductSellingStatus productSellingStatus;

    @Builder
    private Product(String name, String productNumber, int price, int stockQuantity, ProductType productType, ProductSellingStatus productSellingStatus) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productType = productType;
        this.productSellingStatus = productSellingStatus;
    }

    public void removeStock(int count){
        int restStock = stockQuantity - count;
        if(restStock < 0){
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.stockQuantity = restStock;
    }
}
