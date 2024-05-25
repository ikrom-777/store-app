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
    private Float amount;
    private String status;
    private LocalDateTime date;
    private String product;
    private String description;
    private String category;
    private String image;
    private String price;
    private String barcode;
    private String number;
    private String color;
    private String unit;
}
