package com.lian.marketing.lianstockmicroservice.domain.spi;

import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;

public interface ISubcategoryPersistencePort {
    void saveSubcategory(Subcategory subcategory);
    boolean isSubcategoryExist(String name);
}
