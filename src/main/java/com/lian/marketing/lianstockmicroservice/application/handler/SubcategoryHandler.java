package com.lian.marketing.lianstockmicroservice.application.handler;

import com.lian.marketing.lianstockmicroservice.application.dto.request.CreateSubcategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.request.UpdateSubcategoryRequest;
import com.lian.marketing.lianstockmicroservice.application.dto.response.SubcategoryFullResponse;
import com.lian.marketing.lianstockmicroservice.application.dto.response.SubcategoryItemResponse;
import com.lian.marketing.lianstockmicroservice.application.dto.response.SubcategoryResponse;
import com.lian.marketing.lianstockmicroservice.application.mapper.ISubcategoryMapper;
import com.lian.marketing.lianstockmicroservice.domain.api.ISubcategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubcategoryHandler {

    private final ISubcategoryMapper subcategoryMapper;
    private final ISubcategoryServicePort subcategoryServicePort;

    public SubcategoryItemResponse createSubcategory(CreateSubcategoryRequest request) {
        Subcategory subcategoryItem = subcategoryServicePort.createSubcategory(subcategoryMapper.toModelFromRequest(request));
        return subcategoryMapper.fromModelToItemResponse(subcategoryItem);
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
    public ContentPage<SubcategoryItemResponse> findAllSubcategoriesByCategory(int page, int size, boolean isAsc, String sortBy, UUID categoryId) {
        ContentPage<Subcategory> contentPage = subcategoryServicePort.findAllSubcategoriesByCategory(page, size, isAsc, sortBy,categoryId);
        List<SubcategoryItemResponse> subcategoryResponseList = subcategoryMapper.toItemResponseModelListFromModelList(contentPage.getContent());
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

    public SubcategoryFullResponse findSubcategoryById(UUID id) {
        Subcategory subcategory = subcategoryServicePort.findSubcategoryById(id);
        return subcategoryMapper.fromModelToFullResponse(subcategory);
    }

    public void updateCategory(UpdateSubcategoryRequest request) {
        subcategoryServicePort.updateSubcategory(subcategoryMapper.toModelFromUpdateRequest(request));
    }

    public void deleteSubcategoryById(UUID id) {
        subcategoryServicePort.deleteSubcategoryById(id);
    }

}
