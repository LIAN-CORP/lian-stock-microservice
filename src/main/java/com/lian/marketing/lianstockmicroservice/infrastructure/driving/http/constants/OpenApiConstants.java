package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.constants;

public class OpenApiConstants {
    private OpenApiConstants() {}

    // Swagger
    public static final String CREATED_STATUS_CODE = "201";
    public static final String OK_STATUS_CODE = "200";
    public static final String BAD_REQUEST_STATUS_CODE = "400";
    public static final String NOT_FOUND_STATUS_CODE = "404";
    public static final String MEDIATYPE_JSON = "application/json";

    //Category
    public static final String CATEGORY_POST_SUMMARY = "Save new category";
    public static final String CATEGORY_DESCRIPTION_201 = "Category saved";
    public static final String CATEGORY_DESCRIPTION_400 = "Invalid category";
    public static final String CATEGORY_DESCRIPTION_404 = "Category or categories records not found";
    public static final String CATEGORIES_GET_ALL_SUMMARY = "Get all categories paged by page, size and is ascending params";
    public static final String CATEGORIES_GET_ALL_DESCRIPTION_201 = "Found categories records";

    //Paged
    public static final String CATEGORIES_DEFAULT_PAGE = "0";
    public static final String CATEGORIES_DEFAULT_SIZE = "10";
    public static final String CATEGORIES_DEFAULT_IS_ASC = "true";
}
