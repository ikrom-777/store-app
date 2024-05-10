package com.example.storebackend.service;

import com.example.storebackend.dto.BuyStoreProductDto;
import com.example.storebackend.dto.StoreProductDto;
import com.example.storebackend.entity.Product;
import com.example.storebackend.entity.ProductHistory;
import com.example.storebackend.entity.Store;
import com.example.storebackend.entity.StoreProducts;
import com.example.storebackend.repository.ProductHistoryRepository;
import com.example.storebackend.repository.ProductRepository;
import com.example.storebackend.repository.StoreProductRepository;
import com.example.storebackend.repository.StoreRepository;
import com.example.storebackend.user.User;
import com.example.storebackend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreProductService {

    private final StoreProductRepository storeProductRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final ProductHistoryRepository productHistoryRepository;

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
}
