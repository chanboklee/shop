package com.lee.shop.api.service.product.request;

import com.lee.shop.domain.product.Product;
import com.lee.shop.domain.product.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCreateServiceRequest {

    private String name;
    private int price;
    private int stockQuantity;
    private ProductType productType;

    @Builder
    private ProductCreateServiceRequest(String name, int price, int stockQuantity, ProductType productType) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productType = productType;
    }

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .productType(productType)
                .build();
    }
}
