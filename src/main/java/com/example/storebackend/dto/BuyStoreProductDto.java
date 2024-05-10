package com.example.storebackend.dto;

import com.example.storebackend.entity.Product;
import com.example.storebackend.entity.Store;
import com.example.storebackend.user.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyStoreProductDto {
    private Float amount;
    private Float uniquePrice;
    private Float totalPrice;
    private String customer;
    private String comment;
    private Long productId;
    private Long storeId;
}
