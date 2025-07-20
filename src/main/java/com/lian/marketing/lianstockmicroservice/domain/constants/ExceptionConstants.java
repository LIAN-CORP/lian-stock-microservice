package com.lian.marketing.lianstockmicroservice.domain.constants;

public class ExceptionConstants {
    private ExceptionConstants() {}

    public static final String CATEGORY_ALREADY_EXISTS_WITH_NAME = "Category already exists with name: %s";
    public static final String NO_RECORDS_FOR_CATEGORIES = "No records for categories";
    public static final String CATEGORY_WITH_ID_NOT_EXISTS = "Category with id %s not exists";

    public static final String SUBCATEGORY_ALREADY_EXISTS_WITH_NAME = "Subcategory already exists with name: %s";
    public static final String NO_RECORDS_FOR_SUBCATEGORIES = "No records for subcategories";
    public static final String SUBCATEGORY_WITH_ID_NOT_EXISTS = "Subcategory with id %s not exists";

    public static final String NO_RECORDS_FOR_PRODUCTS = "No records for products";

    public static final String FILE_UPLOAD_FAILED = "File upload failed: %s";
    public static final String SDK_AMAZON_EXCEPTION = "SDK AWS exception: %s";
    public static final String FILE_DOWNLOAD_FAILED = "File download failed: %s";
    public static final String AMAZON_S3_CLIENT_FAILED = "Amazon S3 Client failed: %s";

    public static final String PRODUCT_NOT_FOUND = "Product not found";
    public static final String PRODUCT_PRICE_SELL_DO_NOT_MATCH = "Product price sell do not match";
    public static final String PRODUCT_IS_NOT_IN_STOCK = "Product is not in stock";
}
