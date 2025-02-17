package com.lian.marketing.lianstockmicroservice.application.handler;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateCategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.CategoryResponse;
import com.lian.marketing.lianstockmicroservice.application.mapper.ICategoryMapper;
import com.lian.marketing.lianstockmicroservice.domain.api.ICategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryHandler {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryMapper categoryMapper;

    public void saveCategory(CreateCategoryRequest request) {
        categoryServicePort.createCategory(categoryMapper.fromRequestToDtoCategory(request));
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
}
