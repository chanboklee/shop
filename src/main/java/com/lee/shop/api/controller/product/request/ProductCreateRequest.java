package com.lee.shop.api.controller.product.request;

import com.lee.shop.api.service.product.request.ProductCreateServiceRequest;
import com.lee.shop.domain.product.ProductSellingStatus;
import com.lee.shop.domain.product.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCreateRequest {

    @NotBlank(message = "상품명은 필수입니다.")
    private String name;
    @Positive(message = "상품 가격은 양수여야 합니다.")
    private int price;
    @Positive(message = "재고는 양수여야 합니다.")
    private int stockQuantity;
    @NotNull(message = "상품 타입은 필수입니다.")
    private ProductType productType;
    @NotNull(message = "상품 판매상태는 필수입니다.")
    private ProductSellingStatus productSellingStatus;

    @Builder
    private ProductCreateRequest(String name, int price, int stockQuantity, ProductType productType, ProductSellingStatus productSellingStatus) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productType = productType;
        this.productSellingStatus = productSellingStatus;
    }

    public ProductCreateServiceRequest toServiceRequest(){
        return ProductCreateServiceRequest.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .productType(productType)
                .productSellingStatus(productSellingStatus)
                .build();
    }
}
