package com.lian.marketing.lianstockmicroservice.domain.spi;

import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;

import java.util.Optional;
import java.util.UUID;

public interface ICategoryPersistencePort {
    Category saveCategory(Category category);
    boolean isCategoryExistsByName(String name);
    ContentPage<Category> findAllCategories(int page, int size, boolean isAsc);
    boolean categoryExistsByUUID(UUID uuid);
    Optional<Category> findCategoryById(UUID id);
    void updateCategory(Category category);
    void deleteCategoryById(UUID id);
}
