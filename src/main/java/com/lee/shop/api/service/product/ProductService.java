package com.lee.shop.api.service.product;

import com.lee.shop.api.service.product.request.ProductCreateServiceRequest;
import com.lee.shop.api.service.product.response.ProductResponse;
import com.lee.shop.domain.product.Product;
import com.lee.shop.domain.product.ProductRepository;
import com.lee.shop.domain.product.ProductSellingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest productCreateServiceRequest){
        String productNumber = createProductNumber();

        Product product = productCreateServiceRequest.toEntity(productNumber);
        Product saveProduct = productRepository.save(product);
        return ProductResponse.of(saveProduct);
    }

    private String createProductNumber() {
        Product product = productRepository.findTop1ByOrderByIdDesc();
        if(product == null){
            return "001";
        }
        int latestProductNumberInt = Integer.valueOf(product.getProductNumber());
        int nextProductNumberInt = latestProductNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllByProductSellingStatusIn(ProductSellingStatus.forDisplay());
        return products.stream()
                .map(product -> ProductResponse.of(product))
                .collect(Collectors.toList());
    }
}
