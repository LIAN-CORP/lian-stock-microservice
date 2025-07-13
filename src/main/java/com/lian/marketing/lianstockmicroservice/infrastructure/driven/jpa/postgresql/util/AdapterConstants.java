package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.util;

import java.util.HashMap;
import java.util.Map;

public class AdapterConstants {
    private AdapterConstants() {}

    private static final Map<String, String> sortMappingSubcategory = new HashMap<>() {{
        put("category", "category.name");
        put("subcategory", "name");
    }};

    public static String getValueSortMappingSubcategory(String field) {
        return sortMappingSubcategory.getOrDefault(field, "name");
    }

    private static final Map<String, String> sortMappingProducts = new HashMap<>() {{
        put("product", "name");
        put("subcategory", "subcategory.name");
        put("category", "subcategory.category.name");
    }};

    public static String getValueSortMappingProducts(String field) {
        return sortMappingProducts.getOrDefault(field, "name");
    }

}
