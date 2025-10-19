package com.lian.marketing.lianstockmicroservice.application.mapper;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateCategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.request.UpdateCategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.CategoryResponse;
import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface ICategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category fromRequestToDtoCategory(CreateCategoryRequest createCategoryRequest);
    CategoryResponse fromModelToCategoryResponse(Category category);
    List<CategoryResponse> toListCategoryResponse(List<Category> categoryList);
    Category toModelFromUpdateRequest(UpdateCategoryRequest request);
}
