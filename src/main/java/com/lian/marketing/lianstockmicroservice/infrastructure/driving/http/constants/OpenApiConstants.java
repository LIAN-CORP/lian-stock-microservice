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
    public static final String CATEGORY_GET_BY_ID_SUMMARY = "Get category by id";
    public static final String CATEGORY_DESCRIPTION_200 = "Category saved or updated";
    public static final String CATEGORY_PUT_SUMMARY = "Update category by id";
    public static final String CATEGORY_DELETE_SUMMARY = "Delete category by id";
    public static final String CATEGORY_DELETE_DESCRIPTION_204 = "Category deleted";

    //Propduct
    public static final String PRODUCT_POST_SUMMARY = "Save new product category";
    public static final String PRODUCT_DESCRIPTION_201 = "Product saved";
    public static final String PRODUCT_DESCRIPTION_400 = "Invalid product";
    public static final String PRODUCT_DESCRIPTION_404 = "Product or products records not found";
    public static final String PRODUCT_IMAGE_OPTIONAL = "Image product is optional";
    public static final String PRODUCT_GET_ALL_SUMMARY = "Get all products paged by page, size and is ascending params";
    public static final String PRODUCT_GET_ALL_DESCRIPTION_200 = "Found product records";

    //Paged
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";
    public static final String DEFAULT_IS_ASC = "true";
    public static final String DEFAULT_SORT_BY = "name";

    //Subcategory
    public static final String SUBCATEGORY_POST_SUMMARY = "Save new subcategory";
    public static final String SUBCATEGORY_DESCRIPTION_201 = "Subcategory saved";
    public static final String SUBCATEGORY_DESCRIPTION_400 = "Invalid subcategory";
    public static final String SUBCATEGORY_DESCRIPTION_404 = "Subcategory records not found";
    public static final String SUBCATEGORY_GET_ALL_SUMMARY = "Get all subcategories paged by page, size and is ascending params";
    public static final String SUBCATEGORY_GET_ALL_DESCRIPTION_200 = "Found subcategories records";
}
