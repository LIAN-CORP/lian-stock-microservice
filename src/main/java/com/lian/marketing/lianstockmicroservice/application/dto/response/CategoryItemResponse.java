package com.lian.marketing.lianstockmicroservice.application.dto.response;

import java.util.UUID;

public record CategoryItemResponse(
        UUID id,
        String name
) {
}
