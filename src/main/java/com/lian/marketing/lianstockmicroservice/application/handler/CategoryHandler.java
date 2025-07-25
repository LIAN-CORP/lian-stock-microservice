package com.lian.marketing.lianstockmicroservice.application.handler;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateCategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.request.UpdateCategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.CategoryResponse;
import com.lian.marketing.lianstockmicroservice.application.mapper.ICategoryMapper;
import com.lian.marketing.lianstockmicroservice.domain.api.ICategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryHandler {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryMapper categoryMapper;

    public CategoryResponse saveCategory(CreateCategoryRequest request) {
        Category categoryItem = categoryServicePort.createCategory(categoryMapper.fromRequestToDtoCategory(request));
        return categoryMapper.fromModelToCategoryResponse(categoryItem);
    }

    public ContentPage<CategoryResponse> findAllCategories(int page, int size, boolean isAsc) {
        ContentPage<Category> contentPage = categoryServicePort.findAllCategories(page, size, isAsc);
        List<CategoryResponse> categoryResponseList = categoryMapper.toListCategoryResponse(contentPage.getContent());
        return new ContentPage<>(
                contentPage.getTotalPage(),
                contentPage.getTotalElements(),
                contentPage.getPageNumber(),
                contentPage.getPageSize(),
                contentPage.isFirst(),
                contentPage.isLast(),
                categoryResponseList
        );
    }

    public CategoryResponse findCategoryById(UUID id) {
        Category category = categoryServicePort.findCategoryById(id);
        return categoryMapper.fromModelToCategoryResponse(category);
    }

    public void updateCategory(UpdateCategoryRequest request) {
        categoryServicePort.updateCategory(categoryMapper.toModelFromUpdateRequest(request));
    }
}
