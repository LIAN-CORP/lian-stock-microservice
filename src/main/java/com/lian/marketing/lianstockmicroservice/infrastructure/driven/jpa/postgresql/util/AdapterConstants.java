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
}
