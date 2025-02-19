package com.lian.marketing.lianstockmicroservice.domain.api;

import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;

public interface ISubcategoryServicePort {
    void createSubcategory(Subcategory subcategory);
}
