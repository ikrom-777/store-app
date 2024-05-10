package com.example.storebackend.service;

import com.example.storebackend.dto.ColorDto;
import com.example.storebackend.entity.Color;
import com.example.storebackend.repository.ColorRepository;
import com.example.storebackend.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorService {

    private final ColorRepository repository;

    public String addColor(ColorDto request, Authentication auth) {
        User user = (User) auth.getPrincipal();

        Color color = new Color();
        color.setName(request.getName());
        color.setDescription(request.getDescription());
        Color save = repository.save(color);
        return "Color added successfully->" + save.getName();
    }
}
