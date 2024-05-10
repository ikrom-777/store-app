package com.example.storebackend.controller;

import com.example.storebackend.dto.StoreDto;
import com.example.storebackend.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/add")
    public ResponseEntity<?> addStore(@RequestBody StoreDto request, Authentication auth) {
        String message = storeService.addStore(request, auth);
        return ResponseEntity.ok(message);
    }
}
