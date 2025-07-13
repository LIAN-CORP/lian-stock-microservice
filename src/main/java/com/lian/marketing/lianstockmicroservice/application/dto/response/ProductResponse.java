package com.lian.marketing.lianstockmicroservice.application.dto.response;

import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        Integer stock,
        Double priceSell,
        Double priceBuy,
        String subcategory,
        String category
        //SubcategoryItem subcategory
) {
}
