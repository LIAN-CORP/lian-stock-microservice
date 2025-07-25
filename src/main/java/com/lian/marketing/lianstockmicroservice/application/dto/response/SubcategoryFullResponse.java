package com.lian.marketing.lianstockmicroservice.application.dto.response;

import java.util.UUID;

public record SubcategoryFullResponse(
        UUID id,
        String name,
        String description,
        CategoryResponse category
) {
}
