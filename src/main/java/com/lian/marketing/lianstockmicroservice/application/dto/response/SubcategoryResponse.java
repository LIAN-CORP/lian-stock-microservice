package com.lian.marketing.lianstockmicroservice.application.dto.response;

public record SubcategoryResponse(
        String name,
        String description,
        CategoryItemResponse category
) {
}
