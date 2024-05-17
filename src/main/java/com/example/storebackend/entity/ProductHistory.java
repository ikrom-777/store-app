package com.example.storebackend.entity;

import com.example.storebackend.common.BaseEntity;
import com.example.storebackend.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductHistory extends BaseEntity {
    private String action;
    private Float amount;
    private Float uniquePrice;
    private Float totalPrice;
    private String customer;
    private String comment;
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;
}
