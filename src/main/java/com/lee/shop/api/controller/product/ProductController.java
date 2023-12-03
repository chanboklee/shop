package com.lee.shop.api.controller.product;

import com.lee.shop.api.ApiResponse;
import com.lee.shop.api.controller.product.request.ProductCreateRequest;
import com.lee.shop.api.service.product.ProductService;
import com.lee.shop.api.service.product.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest productCreateRequest){
        return ApiResponse.ok(productService.createProduct(productCreateRequest.toServiceRequest()));
    }

    @GetMapping("/products")
    public ApiResponse<List<ProductResponse>> getSellingProducts(){
        return ApiResponse.ok(productService.getSellingProducts());
    }
}
