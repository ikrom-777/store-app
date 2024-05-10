package com.example.storebackend.entity;

import com.example.storebackend.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StoreProducts extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "store",referencedColumnName = "id")
    private Store store;
    @ManyToOne
    @JoinColumn(name = "product",referencedColumnName = "id")
    private Product product;
    private Float amount;
    private String status;
    private LocalDateTime date;

}
