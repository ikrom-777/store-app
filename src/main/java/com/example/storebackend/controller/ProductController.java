package com.example.storebackend.controller;

import com.example.storebackend.dto.ProductRequest;
import com.example.storebackend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody  @Valid ProductRequest request, Authentication auth) {
        String message = productService.addProduct(request,auth);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/update/{product-id}")
    public ResponseEntity<?> editProduct(@PathVariable(value = "product-id") Long id, @RequestBody  @Valid ProductRequest request, Authentication auth) {
        String message = productService.editProduct(id, request, auth);
        return ResponseEntity.ok(message);
    }
}
