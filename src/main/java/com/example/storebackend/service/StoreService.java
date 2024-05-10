package com.example.storebackend.service;

import com.example.storebackend.dto.StoreDto;
import com.example.storebackend.entity.Store;
import com.example.storebackend.repository.StoreRepository;
import com.example.storebackend.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository repository;

    public String addStore(StoreDto request, Authentication auth) {
        User user = (User) auth.getPrincipal();

        Store store = new Store();
        store.setName(request.getName());
        store.setAddress(request.getAddress());
        store.setPhone(request.getPhone());
        Store save = repository.save(store);
        return "Successfully added store->" + save.getName();
    }
}
