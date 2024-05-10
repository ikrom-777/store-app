package com.example.storebackend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    @NotEmpty(message = "name is not empty")
    @NotNull(message = "name is not null")
    private String name;
    private String address;
    private String phone;
}
