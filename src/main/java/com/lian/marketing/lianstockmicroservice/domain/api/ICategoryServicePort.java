package com.lian.marketing.lianstockmicroservice.domain.api;

import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;

import java.util.UUID;

public interface ICategoryServicePort {
    Category createCategory(Category category);
    ContentPage<Category> findAllCategories(int page, int size, boolean isAsc);
    boolean categoryExistsByUUID(UUID uuid);
}
