package com.lee.shop.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

     Product findTop1ByOrderByIdDesc();
}
