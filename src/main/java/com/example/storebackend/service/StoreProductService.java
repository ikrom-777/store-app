package com.example.storebackend.service;

import com.example.storebackend.dto.BuyStoreProductDto;
import com.example.storebackend.dto.StoreProductDto;
import com.example.storebackend.dto.StoreProductTwoDto;
import com.example.storebackend.dto.StoreProductsResponse;
import com.example.storebackend.entity.*;
import com.example.storebackend.repository.*;
import com.example.storebackend.user.User;
import com.example.storebackend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreProductService {

    private final StoreProductRepository storeProductRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final ProductHistoryRepository productHistoryRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;

    public String addStoreProduct(StoreProductDto request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();


        Optional<Store> optionalStore = storeRepository.findById(request.getStoreId());
        if (optionalStore.isEmpty()) {
            throw new RuntimeException("Store not found");
        }
        Optional<Product> optionalProduct = productRepository.findById(request.getProductId());
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        Store store = optionalStore.get();
        Product product = optionalProduct.get();
        product.setPrice(request.getPrice());
        productRepository.save(product);

        Float defaultAmount = 0F;
        StoreProducts storeProducts = null;

        Optional<StoreProducts> optionalStoreProducts = storeProductRepository.findByProductId(request.getProductId());
        if (optionalStoreProducts.isEmpty()) {
            storeProducts = new StoreProducts();
        } else {
            storeProducts = optionalStoreProducts.get();
            defaultAmount = storeProducts.getAmount();
        }


        storeProducts.setStore(store);
        storeProducts.setProduct(product);
        storeProducts.setAmount(defaultAmount + request.getAmount());
        storeProducts.setStatus("ACTIVE");
        storeProducts.setDate(LocalDateTime.now());
        StoreProducts save = storeProductRepository.save(storeProducts);

        ProductHistory newProductHistory = new ProductHistory();
        newProductHistory.setProduct(product);
        newProductHistory.setStore(store);
        newProductHistory.setAmount(request.getAmount());
        newProductHistory.setAction("INCOME");
        newProductHistory.setUniquePrice(request.getPrice());
        newProductHistory.setTime(LocalDateTime.now());
        newProductHistory.setUser(user);
        productHistoryRepository.save(newProductHistory);

        return "Store product added successfully->" + save.getProduct().getName();
    }

    public String buyProductFromStore(BuyStoreProductDto request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();


        Optional<Store> optionalStore = storeRepository.findById(request.getStoreId());
        if (optionalStore.isEmpty()) {
            throw new RuntimeException("Store not found");
        }
        Optional<Product> optionalProduct = productRepository.findById(request.getProductId());
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        Store store = optionalStore.get();
        Product product = optionalProduct.get();

        Float defaultAmount = 0F;
        StoreProducts storeProducts = null;

        Optional<StoreProducts> optionalStoreProducts = storeProductRepository.findByProductId(request.getProductId());
        if (optionalStoreProducts.isEmpty()) {
            storeProducts = new StoreProducts();
        } else {
            storeProducts = optionalStoreProducts.get();
            defaultAmount = storeProducts.getAmount();
        }


        storeProducts.setStore(store);
        storeProducts.setProduct(product);
        storeProducts.setAmount(defaultAmount - request.getAmount());
        storeProducts.setStatus("ACTIVE");
        storeProducts.setDate(LocalDateTime.now());
        StoreProducts save = storeProductRepository.save(storeProducts);

        ProductHistory newProductHistory = new ProductHistory();
        newProductHistory.setProduct(product);
        newProductHistory.setStore(store);
        newProductHistory.setAmount(request.getAmount());
        newProductHistory.setAction("OUTCOME");
        newProductHistory.setUniquePrice(product.getPrice());
        newProductHistory.setTotalPrice(request.getTotalPrice());
        newProductHistory.setTime(LocalDateTime.now());
        newProductHistory.setUser(user);
        productHistoryRepository.save(newProductHistory);
        return "Store product updated successfully";
    }

    public List<StoreProductsResponse> getStoreProducts() {
        List<StoreProducts> storeProductsList = storeProductRepository.findAll();
        if (storeProductsList.isEmpty()) {
            throw new RuntimeException("Store products not found");
        }
        List<StoreProductsResponse> storeProductsResponses = new ArrayList<>();
        for (StoreProducts storeProduct : storeProductsList) {
            Optional<Product> optionalProduct = productRepository.findById(storeProduct.getProduct().getId());
            if (optionalProduct.isEmpty()) {
                throw new RuntimeException("Product not found");
            }
            Product product = optionalProduct.get();

            Optional<Category> optionalCategory = categoryRepository.findById(product.getCategory().getId());
            if (optionalCategory.isEmpty()) {
                throw new RuntimeException("Category not found");
            }
            Category category = optionalCategory.get();

            Optional<Color> optionalColor = colorRepository.findById(product.getColor().getId());
            if (optionalColor.isEmpty()) {
                throw new RuntimeException("Color not found");
            }
            Color color = optionalColor.get();
            StoreProductsResponse storeProductsResponse = new StoreProductsResponse(
                    null,
                    storeProduct.getAmount(),
                    storeProduct.getStatus(),
                    storeProduct.getDate(),
                    storeProduct.getProduct().getName(),
                    product.getDescription(),
                    category.getName(),
                    product.getImage(),
                    product.getPrice().toString(),
                    product.getBarcode(),
                    product.getNumber(),
                    color.getName(),
                    product.getUnit()
            );
            storeProductsResponses.add(storeProductsResponse);
        }
        return storeProductsResponses;
    }

    public String addStoreProductTwo(StoreProductTwoDto request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Optional<Color> optionalColor = colorRepository.findById(request.getColor());
        if (optionalColor.isEmpty()) {
            throw new RuntimeException("Color not found");
        }
        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategory());
        if (optionalCategory.isEmpty()) {
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
        Product savedProduct = productRepository.save(product);

        StoreProducts storeProducts = new StoreProducts();

        storeProducts.setStore(null);
        storeProducts.setProduct(savedProduct);
        storeProducts.setAmount(request.getAmount());
        storeProducts.setStatus("ACTIVE");
        storeProducts.setDate(LocalDateTime.now());
        StoreProducts save = storeProductRepository.save(storeProducts);

        ProductHistory newProductHistory = new ProductHistory();
        newProductHistory.setProduct(savedProduct);
        newProductHistory.setStore(null);
        newProductHistory.setAmount(request.getAmount());
        newProductHistory.setAction("INCOME");
        newProductHistory.setUniquePrice(request.getPrice());
        newProductHistory.setTime(LocalDateTime.now());
        newProductHistory.setUser(user);
        productHistoryRepository.save(newProductHistory);


        return "Store product and Product added successfully";
    }
}
