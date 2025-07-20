package com.lian.marketing.lianstockmicroservice.application.dto.request;

import com.lian.marketing.lianstockmicroservice.application.constants.RequestConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record DiscountProductStockRequest(
        @NotNull(message = RequestConstants.PRODUCT_ID_MUST_NOT_BE_NULL)
        UUID id,
        @Positive(message = RequestConstants.PRODUCT_QUANTITY_IS_NOT_VALID)
        Integer quantity
) {
}
