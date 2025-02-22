package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.mapper;

import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity.SubcategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISubcategoryEntityMapper {
    @Mapping(source = "categoryId", target = "category.id")
    SubcategoryEntity fromModelToSubcategoryEntity(Subcategory subcategory);
    @Mapping(source = "category.id", target = "categoryId")
    Subcategory fromEntityToSubcategoryModel(SubcategoryEntity subcategoryEntity);
    List<Subcategory> fromEntityToSubcategoryModelList(List<SubcategoryEntity> subcategoryEntities);
}
