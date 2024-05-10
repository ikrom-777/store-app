package com.example.storebackend.repository;


import com.example.storebackend.entity.StoreProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreProductRepository extends JpaRepository<StoreProducts,Long> {
    Optional<StoreProducts> findByProductId(Long productId);
}
