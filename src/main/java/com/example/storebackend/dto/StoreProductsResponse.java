package com.example.storebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreProductsResponse {
    private String store;
    private String product;
    private Float amount;
    private String status;
    private LocalDateTime date;
}
