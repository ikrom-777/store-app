package com.example.storebackend.controller;

import com.example.storebackend.dto.BuyStoreProductDto;
import com.example.storebackend.dto.StoreProductDto;
import com.example.storebackend.dto.StoreProductsResponse;
import com.example.storebackend.entity.StoreProducts;
import com.example.storebackend.service.StoreProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("store-product")
@RequiredArgsConstructor
public class StoreProductController {

    private final StoreProductService storeProductService;

    @PostMapping("/add")
    public ResponseEntity<?> addStoreProduct(@RequestBody @Valid StoreProductDto request, Authentication authentication) {
        String message = storeProductService.addStoreProduct(request, authentication);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/buy-product")
    public ResponseEntity<?> buyStoreProduct(@RequestBody @Valid BuyStoreProductDto request, Authentication authentication) {
        String message = storeProductService.buyProductFromStore(request, authentication);
        return ResponseEntity.ok(message);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getStoreProduct(Authentication authentication) {
        List<StoreProductsResponse> storeProducts = storeProductService.getStoreProducts();
        return ResponseEntity.ok(storeProducts);
    }
}
