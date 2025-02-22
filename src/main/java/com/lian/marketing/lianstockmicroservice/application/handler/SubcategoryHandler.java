package com.lian.marketing.lianstockmicroservice.application.handler;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateSubcategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.mapper.ISubcategoryMapper;
import com.lian.marketing.lianstockmicroservice.domain.api.ISubcategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubcategoryHandler {

    private final ISubcategoryMapper subcategoryMapper;
    private final ISubcategoryServicePort subcategoryServicePort;

    public void createSubcategory(CreateSubcategoryRequest request) {
        subcategoryServicePort.createSubcategory(subcategoryMapper.toModelFromRequest(request));
    }

}
