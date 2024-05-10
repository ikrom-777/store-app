package com.example.storebackend.entity;

import com.example.storebackend.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Color extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private String code;
}
