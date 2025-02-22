package com.lian.marketing.lianstockmicroservice.application.dto.response;

public record ProductResponse(
        String name,
        String description,
        Integer stock,
        Double priceSell,
        Double priceBuy,
        SubcategoryItem subcategory
) {
}
