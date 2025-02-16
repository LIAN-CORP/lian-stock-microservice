package com.lian.marketing.lianstockmicroservice.application.handler;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateCategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.mapper.ICategoryMapper;
import com.lian.marketing.lianstockmicroservice.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryHandler {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryMapper categoryMapper;

    public void saveCategory(CreateCategoryRequest request) {
        categoryServicePort.createCategory(categoryMapper.fromRequestToDtoCategory(request));
    }
}
