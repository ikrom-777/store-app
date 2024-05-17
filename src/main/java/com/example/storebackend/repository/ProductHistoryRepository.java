package com.example.storebackend.repository;

import com.example.storebackend.entity.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {

}
