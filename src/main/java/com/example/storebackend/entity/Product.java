package com.example.storebackend.entity;

import com.example.storebackend.common.BaseEntity;
import com.example.storebackend.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {
    @ManyToOne
    private Category category;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String unit;
    @Column(nullable = false)
    private Float price;
    @ManyToOne
    private Color color;
    private String barcode;
    private String number;
    private String image;
    @ManyToOne
    private User user;
    private LocalDateTime date;

    @OneToMany(mappedBy = "product")
    private Set<StoreProducts> storeProducts;

}
