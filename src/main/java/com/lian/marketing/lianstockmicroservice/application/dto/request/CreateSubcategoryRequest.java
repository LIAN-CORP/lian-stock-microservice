package com.lian.marketing.lianstockmicroservice.application.dto.request;

import com.lian.marketing.lianstockmicroservice.application.constants.RequestConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateSubcategoryRequest(
    @NotEmpty(message = RequestConstants.SUBCATEGORY_NAME_MUST_NOT_BE_NULL)
    @Size(max = RequestConstants.SUBCATEGORY_NAME_MAX_LENGTH)
    String name,

    @NotEmpty(message = RequestConstants.SUBCATEGORY_DESCRIPTION_MUST_NOT_BE_NULL)
    @Size(max = RequestConstants.SUBCATEGORY_DESCRIPTION_MAX_LENGTH)
    String description,

    @NotEmpty(message = RequestConstants.CATEGORY_ID_MUST_NOT_BE_NULL)
    String categoryId
) {
}
