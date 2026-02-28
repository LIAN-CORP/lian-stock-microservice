package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "price_sell", columnDefinition = "numeric(10,2)", nullable = false)
    private double priceSell;

    @Column(name = "price_buy", columnDefinition = "numeric(10,2)", nullable = false)
    private double priceBuy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subcategory_id", nullable = false)
    private SubcategoryEntity subcategory;

    @Column(name = "image_path")
    private String imagePath;

    @Column(nullable = false)
    private Boolean isActive;
}
