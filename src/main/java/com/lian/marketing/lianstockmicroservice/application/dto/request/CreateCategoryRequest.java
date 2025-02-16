package com.lian.marketing.lianstockmicroservice.application.dto.request;

import com.lian.marketing.lianstockmicroservice.application.constants.RequestConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
        @NotEmpty(message = RequestConstants.CATEGORY_NAME_MUST_NOT_BE_NULL)
        @Size(max = RequestConstants.CATEGORY_NAME_MAX_LENGTH)
        String name,

        @NotEmpty(message = RequestConstants.CATEGORY_DESCRIPTION_MUST_NOT_BE_NULL)
        @Size(max = RequestConstants.CATEGORY_DESCRIPTION_MAX_LENGTH)
        String description
) {
}
