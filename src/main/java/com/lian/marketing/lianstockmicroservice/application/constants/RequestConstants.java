package com.lian.marketing.lianstockmicroservice.application.constants;

public class RequestConstants {
    private RequestConstants() {}

    public static final String CATEGORY_NAME_MUST_NOT_BE_NULL = "Category name must not be null";
    public static final String CATEGORY_DESCRIPTION_MUST_NOT_BE_NULL = "Category description must not be null";
    public static final int CATEGORY_NAME_MAX_LENGTH = 50;
    public static final int CATEGORY_DESCRIPTION_MAX_LENGTH = 150;

    public static final String SUBCATEGORY_NAME_MUST_NOT_BE_NULL = "Subcategory name must not be null";
    public static final String SUBCATEGORY_DESCRIPTION_MUST_NOT_BE_NULL = "Subcategory description must not be null";
    public static final int SUBCATEGORY_NAME_MAX_LENGTH = 50;
    public static final int SUBCATEGORY_DESCRIPTION_MAX_LENGTH = 200;
    public static final String CATEGORY_ID_MUST_NOT_BE_NULL = "Category id must not be null";

    public static final String PRODUCT_NAME_MUST_NOT_BE_NULL = "Product name must not be null";
    public static final String PRODUCT_DESCRIPTION_MUST_NOT_BE_NULL = "Product description must not be null";
    public static final int PRODUCT_NAME_MAX_LENGTH = 100;
    public static final int PRODUCT_DESCRIPTION_MAX_LENGTH = 200;
    public static final String PRODUCT_STOCK_MUST_NOT_BE_NULL = "Product stock must not be null";
    public static final String PRODUCT_STOCK_MUST_BE_POSITIVE_OR_ZERO = "Product stock must be positive or zero";
    public static final String PRODUCT_PRICE_SELL_MUST_NOT_BE_NULL = "Product price sell must not be null";
    public static final String PRODUCT_PRICE_SELL_MUST_BE_POSITIVE = "Product price sell must be positive";
    public static final String PRODUCT_PRICE_BUY_MUST_NOT_BE_NULL = "Product price buy must not be null";
    public static final String PRODUCT_PRICE_BUY_MUST_BE_POSITIVE = "Product price buy must be positive";
    public static final String SUBCATEGORY_ID_MUST_NOT_BE_NULL = "Subcategory id must not be null";
}
