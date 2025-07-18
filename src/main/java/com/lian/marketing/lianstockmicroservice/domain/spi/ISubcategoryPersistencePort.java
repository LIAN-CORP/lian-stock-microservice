package com.lian.marketing.lianstockmicroservice.domain.spi;

import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;

import java.util.UUID;

public interface ISubcategoryPersistencePort {
    void saveSubcategory(Subcategory subcategory);
    boolean isSubcategoryExist(String name);
    ContentPage<Subcategory> findAllSubcategory(int page, int size, boolean isAsc, String sortBy);
    ContentPage<Subcategory> findAllSubcategoryByCategory(int page, int size, boolean isAsc, String sortBy, UUID categoryId);
    boolean subcategoryExistsByUUID(UUID uuid);
}
