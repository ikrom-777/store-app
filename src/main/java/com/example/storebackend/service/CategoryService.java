package com.example.storebackend.service;

import com.example.storebackend.dto.CategoryDto;
import com.example.storebackend.entity.Category;
import com.example.storebackend.repository.CategoryRepository;
import com.example.storebackend.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public String addCategory(CategoryDto category, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Category categoryEntity = new Category();
        categoryEntity.setName(category.getName());
        categoryEntity.setDescription(category.getDescription());
        categoryEntity.setUserId(user.getId());
        Category save = categoryRepository.save(categoryEntity);

        return "Category added successfully->" + save.getName();
    }
}
