package com.lee.shop.api.service.product.request;

import com.lee.shop.domain.product.Product;
import com.lee.shop.domain.product.ProductSellingStatus;
import com.lee.shop.domain.product.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCreateServiceRequest {

    private String productNumber;
    private String name;
    private int price;
    private int stockQuantity;
    private ProductType productType;
    private ProductSellingStatus productSellingStatus;

    @Builder
    private ProductCreateServiceRequest(String productNumber, String name, int price, int stockQuantity, ProductType productType, ProductSellingStatus productSellingStatus) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productType = productType;
        this.productSellingStatus = productSellingStatus;
    }

    public Product toEntity(String productNumber) {
        return Product.builder()
                .name(name)
                .productNumber(productNumber)
                .price(price)
                .stockQuantity(stockQuantity)
                .productType(productType)
                .productSellingStatus(productSellingStatus)
                .build();
    }
}
