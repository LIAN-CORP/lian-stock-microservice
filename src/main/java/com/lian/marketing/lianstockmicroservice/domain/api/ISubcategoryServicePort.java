package com.lian.marketing.lianstockmicroservice.domain.api;

import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;

import java.util.UUID;

public interface ISubcategoryServicePort {
    void createSubcategory(Subcategory subcategory);
    ContentPage<Subcategory> findAllSubcategories(int page, int size, boolean isAsc, String sortBy);
    void subcategoryExistsByUUID(UUID uuid);
}
