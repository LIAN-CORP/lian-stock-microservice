package com.lian.marketing.lianstockmicroservice.domain.api;

import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;

public interface ICategoryServicePort {
    void createCategory(Category category);
    ContentPage<Category> findAllCategories(int page, int size, boolean isAsc);
}
