package com.example.storebackend.dto;

import com.example.storebackend.entity.Category;
import com.example.storebackend.entity.Color;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Long category;
    @NotEmpty(message = "name is not empty")
    @NotNull(message = "name is not null")
    private String name;
    private String description;
    @NotEmpty(message = "unit is not empty")
    @NotNull(message = "unit is not null")
    private String unit;
    @NotNull(message = "price is not null")
    private Float price;
    private Long color;
    private String barcode;
    private String number;
}
