package com.lian.marketing.lianstockmicroservice.domain.constants;

public class ExceptionConstants {
    private ExceptionConstants() {}

    public static final String CATEGORY_ALREADY_EXISTS_WITH_NAME = "Category already exists with name: %s";
    public static final String NO_RECORDS_FOR_CATEGORIES = "No records for categories";
    public static final String CATEGORY_WITH_ID_NOT_EXISTS = "Category with id %s not exists";

    public static final String SUBCATEGORY_ALREADY_EXISTS_WITH_NAME = "Subcategory already exists with name: %s";
    public static final String NO_RECORDS_FOR_SUBCATEGORIES = "No records for subcategories";
    public static final String SUBCATEGORY_WITH_ID_NOT_EXISTS = "Subcategory with id %s not exists";
}
