package com.example.storebackend.service;

import com.example.storebackend.dto.ProductRequest;
import com.example.storebackend.entity.Category;
import com.example.storebackend.entity.Color;
import com.example.storebackend.entity.Product;
import com.example.storebackend.repository.CategoryRepository;
import com.example.storebackend.repository.ColorRepository;
import com.example.storebackend.repository.ProductRepository;
import com.example.storebackend.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;

    public String addProduct(ProductRequest request, Authentication auth) {
        User user = ((User) auth.getPrincipal());

        Optional<Color> optionalColor = colorRepository.findById(request.getColor());
        if(optionalColor.isEmpty()){
            throw new RuntimeException("Color not found");
        }
        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategory());
        if(optionalCategory.isEmpty()){
            throw new RuntimeException("Category not found");
        }
        Category category = optionalCategory.get();
        Color color = optionalColor.get();

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .barcode(request.getBarcode())
                .color(color)
                .date(LocalDateTime.now())
                .category(category)
                .unit(request.getUnit())
                .number(request.getNumber())
                .user(user)
                .build();
        Product save = productRepository.save(product);
        return "Saved-" + save.getId();
    }

    public String editProduct(Long id, ProductRequest request, Authentication auth) {
        User user = ((User) auth.getPrincipal());

        Optional<Color> optionalColor = colorRepository.findById(request.getColor());
        if(optionalColor.isEmpty()){
            throw new RuntimeException("Color not found");
        }
        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategory());
        if(optionalCategory.isEmpty()){
            throw new RuntimeException("Category not found");
        }
        Category category = optionalCategory.get();
        Color color = optionalColor.get();

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        Product product = optionalProduct.get();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setBarcode(request.getBarcode());
        product.setColor(color);
        product.setDate(LocalDateTime.now());
        product.setCategory(category);
        product.setUnit(request.getUnit());
        product.setNumber(request.getNumber());
        product.setUser(user);
        productRepository.save(product);

        return "Edited-" + product.getId();
    }
}
