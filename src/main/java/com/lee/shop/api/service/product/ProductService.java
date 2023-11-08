package com.lee.shop.api.service.product;

import com.lee.shop.api.service.product.request.ProductCreateServiceRequest;
import com.lee.shop.api.service.product.response.ProductCreateResponse;
import com.lee.shop.domain.product.Product;
import com.lee.shop.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductCreateResponse createProduct(ProductCreateServiceRequest productCreateServiceRequest){
        Product product = productCreateServiceRequest.toEntity();
        Product saveProduct = productRepository.save(product);
        return ProductCreateResponse.of(saveProduct);
    }
}
