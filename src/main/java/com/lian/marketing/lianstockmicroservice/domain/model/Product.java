package com.lian.marketing.lianstockmicroservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private UUID id;
    private String name;
    private String description;
    private Double stock;
    private float priceSell;
    private float priceBuy;
    private Subcategory subcategory;
}
