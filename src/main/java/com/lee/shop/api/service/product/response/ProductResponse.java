package com.lee.shop.api.service.product.response;

import com.lee.shop.domain.product.Product;
import com.lee.shop.domain.product.ProductSellingStatus;
import com.lee.shop.domain.product.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {

    private Long id;
    private String productNumber;
    private String name;
    private int price;
    private int stockQuantity;
    private ProductType productType;
    private ProductSellingStatus productSellingStatus;

    @Builder
    private ProductResponse(Long id, String productNumber, String name, int price, int stockQuantity, ProductType productType, ProductSellingStatus productSellingStatus) {
        this.id = id;
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productType = productType;
        this.productSellingStatus = productSellingStatus;
    }

    public static ProductResponse of (Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .productNumber(product.getProductNumber())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .productType(product.getProductType())
                .productSellingStatus(product.getProductSellingStatus())
                .build();
    }
}
