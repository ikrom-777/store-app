package com.example.storebackend.controller;

import com.example.storebackend.dto.ProductHistoryResponse;
import com.example.storebackend.entity.ProductHistory;
import com.example.storebackend.service.ProductHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product/history")
@RequiredArgsConstructor
public class ProductHistoryController {

    private final ProductHistoryService productHistoryService;
    @GetMapping
    public ResponseEntity<?> getProductHistory() {
        List<ProductHistoryResponse> allHistory = productHistoryService.getAllHistory();
        return ResponseEntity.ok(allHistory);
    }
}
