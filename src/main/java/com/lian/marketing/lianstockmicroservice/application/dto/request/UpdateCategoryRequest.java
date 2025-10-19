package com.lian.marketing.lianstockmicroservice.application.dto.request;

import com.lian.marketing.lianstockmicroservice.application.constants.RequestConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateCategoryRequest (
    @NotNull(message = RequestConstants.CATEGORY_ID_MUST_NOT_BE_NULL)
    UUID id,
    @NotEmpty(message = RequestConstants.CATEGORY_NAME_MUST_NOT_BE_NULL)
    String name,
    @NotEmpty(message = RequestConstants.CATEGORY_DESCRIPTION_MUST_NOT_BE_NULL)
    String description
) {

}
