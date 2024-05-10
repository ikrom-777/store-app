package com.example.storebackend.dto;

import com.example.storebackend.entity.Product;
import com.example.storebackend.entity.Store;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreProductDto {
    private Long storeId;
    private Long productId;
    private Float amount;
    private String status;
    private String action;
    private Float price;
}
