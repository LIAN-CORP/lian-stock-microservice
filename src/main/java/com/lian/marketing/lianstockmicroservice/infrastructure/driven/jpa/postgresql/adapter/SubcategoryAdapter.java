package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.adapter;

import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;
import com.lian.marketing.lianstockmicroservice.domain.spi.ISubcategoryPersistencePort;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.mapper.ISubcategoryEntityMapper;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.repository.ISubcategoryRespository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubcategoryAdapter implements ISubcategoryPersistencePort {

    private final ISubcategoryRespository subcategoryRepository;
    private final ISubcategoryEntityMapper subcategoryEntityMapper;

    @Override
    public void saveSubcategory(Subcategory subcategory) {
        subcategoryRepository.save(subcategoryEntityMapper.fromModelToSubcategoryEntity(subcategory));
    }

    @Override
    public boolean isSubcategoryExist(String name) {
        return subcategoryRepository.existsByName(name);
    }
}
