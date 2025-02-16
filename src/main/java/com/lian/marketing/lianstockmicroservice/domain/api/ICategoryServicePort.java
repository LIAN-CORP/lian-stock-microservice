package com.lian.marketing.lianstockmicroservice.domain.api;

import com.lian.marketing.lianstockmicroservice.domain.model.Category;

public interface ICategoryServicePort {
    void createCategory(Category category);
}
