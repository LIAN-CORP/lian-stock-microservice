package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.constants;

public class OpenApiConstants {
    private OpenApiConstants() {}

    // Swagger
    public static final String CREATED_STATUS_CODE = "200";
    public static final String BAD_REQUEST_STATUS_CODE = "400";
    public static final String MEDIATYPE_JSON = "application/json";

    //Category
    public static final String CATEGORY_POST_SUMMARY = "Save new category";
    public static final String CATEGORY_DESCRIPTION_200 = "Category saved";
    public static final String CATEGORY_DESCRIPTION_400 = "Invalid category";

    //Paged
    public static final String CATEGORIES_DEFAULT_PAGE = "0";
    public static final String CATEGORIES_DEFAULT_SIZE = "10";
    public static final String CATEGORIES_DEFAULT_IS_ASC = "true";
}
