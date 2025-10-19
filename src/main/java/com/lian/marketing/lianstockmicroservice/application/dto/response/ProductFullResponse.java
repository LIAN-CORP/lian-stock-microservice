package com.lian.marketing.lianstockmicroservice.application.dto.response;

import java.util.UUID;

public record ProductFullResponse(
        UUID id,
        String name,
        String description,
        Integer stock,
        Double priceSell,
        Double priceBuy,
        UUID subcategoryId,
        UUID categoryId,
        String imagePath
) {
}
