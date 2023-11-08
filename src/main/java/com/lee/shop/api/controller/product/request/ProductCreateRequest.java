package com.lee.shop.api.controller.product.request;

import com.lee.shop.api.service.product.request.ProductCreateServiceRequest;
import com.lee.shop.domain.product.ProductType;
import lombok.Getter;

@Getter
public class ProductCreateRequest {

    private String name;
    private int price;
    private int stockQuantity;
    private ProductType productType;

    public ProductCreateServiceRequest toServiceRequest(){
        return ProductCreateServiceRequest.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .productType(productType)
                .build();
    }
}
