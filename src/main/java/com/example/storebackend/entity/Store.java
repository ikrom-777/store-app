package com.example.storebackend.entity;

import com.example.storebackend.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Store extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "store")
    private Set<StoreProducts> storeProducts;
}
