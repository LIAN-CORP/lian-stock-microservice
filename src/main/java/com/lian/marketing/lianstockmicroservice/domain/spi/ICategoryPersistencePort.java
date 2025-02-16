package com.lian.marketing.lianstockmicroservice.domain.spi;

import com.lian.marketing.lianstockmicroservice.domain.model.Category;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    boolean isCategoryExistsByName(String name);
}
