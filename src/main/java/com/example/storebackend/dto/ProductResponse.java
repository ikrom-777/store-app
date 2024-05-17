package com.example.storebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String category;
    private String name;
    private String description;
    private String unit;
    private Float price;
    private String color;
    private String barcode;
    private String number;
    private String image;
    private String user;
    private LocalDateTime date;
}
