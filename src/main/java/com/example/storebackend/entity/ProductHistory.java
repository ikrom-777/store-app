package com.example.storebackend.entity;

import com.example.storebackend.common.BaseEntity;
import com.example.storebackend.user.User;
import jakarta.persistence.Entity;
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

    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;
    @ManyToOne
    private Store store;
}
