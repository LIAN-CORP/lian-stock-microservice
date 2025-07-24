package com.lian.marketing.lianstockmicroservice.application.dto.response;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        String description
) {
}
