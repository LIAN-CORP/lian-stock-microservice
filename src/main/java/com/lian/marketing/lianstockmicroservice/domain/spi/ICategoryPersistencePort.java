package com.lian.marketing.lianstockmicroservice.domain.spi;

import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    boolean isCategoryExistsByName(String name);
    ContentPage<Category> findAllCategories(int page, int size, boolean isAsc);
}
