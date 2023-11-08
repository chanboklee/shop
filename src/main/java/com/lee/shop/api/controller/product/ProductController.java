package com.lee.shop.api.controller.product;

import com.lee.shop.api.controller.product.request.ProductCreateRequest;
import com.lee.shop.api.service.product.ProductService;
import com.lee.shop.api.service.product.response.ProductCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductCreateResponse createProduct(@RequestBody ProductCreateRequest productCreateRequest){
        return productService.createProduct(productCreateRequest.toServiceRequest());
    }
}
