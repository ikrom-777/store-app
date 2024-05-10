package com.example.storebackend.controller;

import com.example.storebackend.dto.ColorDto;
import com.example.storebackend.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("color")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @PostMapping("/add")
    public ResponseEntity<?> addColor(@RequestBody ColorDto request, Authentication auth) {
        String message = colorService.addColor(request, auth);
        return ResponseEntity.ok(message);
    }
}
