package com.lian.marketing.lianstockmicroservice.application.dto.request;

import com.lian.marketing.lianstockmicroservice.application.constants.RequestConstants;
import jakarta.validation.constraints.*;

public record CreateProductRequest(
    @NotEmpty(message = RequestConstants.PRODUCT_NAME_MUST_NOT_BE_NULL)
    @Size(max = RequestConstants.PRODUCT_NAME_MAX_LENGTH)
    String name,

    @NotEmpty(message = RequestConstants.PRODUCT_DESCRIPTION_MUST_NOT_BE_NULL)
    @Size(max = RequestConstants.PRODUCT_DESCRIPTION_MAX_LENGTH)
    String description,

    @NotNull(message = RequestConstants.PRODUCT_STOCK_MUST_NOT_BE_NULL)
    @PositiveOrZero(message = RequestConstants.PRODUCT_STOCK_MUST_BE_POSITIVE_OR_ZERO)
    Integer stock,

    @NotNull(message = RequestConstants.PRODUCT_PRICE_SELL_MUST_NOT_BE_NULL)
    @Positive(message = RequestConstants.PRODUCT_PRICE_SELL_MUST_BE_POSITIVE)
    Double priceSell,

    @NotNull(message = RequestConstants.PRODUCT_PRICE_BUY_MUST_NOT_BE_NULL)
    @Positive(message = RequestConstants.PRODUCT_PRICE_BUY_MUST_BE_POSITIVE)
    Double priceBuy,

    @NotNull(message = RequestConstants.SUBCATEGORY_ID_MUST_NOT_BE_NULL)
    String subcategoryId
) {
}
