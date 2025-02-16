package com.lian.marketing.lianstockmicroservice.application.mapper;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateCategoryRequest;
import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface ICategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category fromRequestToDtoCategory(CreateCategoryRequest createCategoryRequest);
}
