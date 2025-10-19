package com.lian.marketing.lianstockmicroservice.domain.api;

import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;

import java.util.UUID;

public interface ISubcategoryServicePort {
    Subcategory createSubcategory(Subcategory subcategory);
    ContentPage<Subcategory> findAllSubcategories(int page, int size, boolean isAsc, String sortBy);
    ContentPage<Subcategory> findAllSubcategoriesByCategory(int page, int size, boolean isAsc, String sortBy, UUID categoryId);
    void subcategoryExistsByUUID(UUID uuid);
    Subcategory findSubcategoryById(UUID id);
    void updateSubcategory(Subcategory subcategory);
    void deleteSubcategoryById(UUID id);
}
