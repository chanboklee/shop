package com.lee.shop.api.service.product.response;

import com.lee.shop.domain.product.Product;
import com.lee.shop.domain.product.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private ProductType productType;

    @Builder
    private ProductResponse(Long id, String name, int price, int stockQuantity, ProductType productType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productType = productType;
    }

    public static ProductResponse of (Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .productType(product.getProductType())
                .build();
    }
}
