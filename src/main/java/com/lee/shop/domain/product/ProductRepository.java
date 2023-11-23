package com.lee.shop.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

     Product findTop1ByOrderByIdDesc();

     List<Product> findAllByProductSellingStatusIn(List<ProductSellingStatus> productSellingStatusList);
}
