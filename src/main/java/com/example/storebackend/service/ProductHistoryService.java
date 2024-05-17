package com.example.storebackend.service;

import com.example.storebackend.dto.ProductHistoryResponse;
import com.example.storebackend.entity.ProductHistory;
import com.example.storebackend.repository.ProductHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductHistoryService {
    private final ProductHistoryRepository productHistoryRepository;


    public List<ProductHistoryResponse> getAllHistory() {
        List<ProductHistory> all = productHistoryRepository.findAll();
        if (all.isEmpty()) {
            throw new  RuntimeException("Product history is empty");
        }
        List<ProductHistoryResponse> responses = new ArrayList<>();

        for (ProductHistory productHistory : all) {
            ProductHistoryResponse productHistoryResponse = new ProductHistoryResponse(
                    productHistory.getAction(),
                    productHistory.getAmount(),
                    productHistory.getUniquePrice(),
                    productHistory.getTotalPrice(),
                    productHistory.getCustomer(),
                    productHistory.getComment(),
                    productHistory.getTime(),
                    productHistory.getProduct().getName(),
                    productHistory.getUser().getName(),
                    productHistory.getStore().getName()
            );
            responses.add(productHistoryResponse);
        }
        return responses;
    }
}
