package com.lian.marketing.lianstockmicroservice.application.handler;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateSubcategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.SubcategoryResponse;
import com.lian.marketing.lianstockmicroservice.application.mapper.ISubcategoryMapper;
import com.lian.marketing.lianstockmicroservice.domain.api.ISubcategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryHandler {

    private final ISubcategoryMapper subcategoryMapper;
    private final ISubcategoryServicePort subcategoryServicePort;

    public void createSubcategory(CreateSubcategoryRequest request) {
        subcategoryServicePort.createSubcategory(subcategoryMapper.toModelFromRequest(request));
    }

    public ContentPage<SubcategoryResponse> findAllSubcategories(int page, int size, boolean isAsc, String sortBy) {
        ContentPage<Subcategory> contentPage = subcategoryServicePort.findAllSubcategories(page, size, isAsc, sortBy);
        List<SubcategoryResponse> subcategoryResponseList = subcategoryMapper.toResponseModelListFromModelList(contentPage.getContent());
        return new ContentPage<>(
                contentPage.getTotalPage(),
                contentPage.getTotalElements(),
                contentPage.getPageNumber(),
                contentPage.getPageSize(),
                contentPage.isFirst(),
                contentPage.isLast(),
                subcategoryResponseList
        );
    }

}
