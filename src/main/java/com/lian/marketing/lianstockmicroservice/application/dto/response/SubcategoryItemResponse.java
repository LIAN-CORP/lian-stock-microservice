package com.lian.marketing.lianstockmicroservice.application.dto.response;

import java.util.UUID;

public record SubcategoryItemResponse(
        UUID id,
        String name
) {
}
