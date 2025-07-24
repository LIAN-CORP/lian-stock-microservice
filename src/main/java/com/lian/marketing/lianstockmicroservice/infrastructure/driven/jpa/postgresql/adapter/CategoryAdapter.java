package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.adapter;

import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.spi.ICategoryPersistencePort;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity.CategoryEntity;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.mapper.ICategoryEntityMapper;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        CategoryEntity categoryEntity = categoryRepository.save(categoryEntityMapper.fromModelToCategoryEntity(category));
        return categoryEntityMapper.fromEntityToCategoryModel(categoryEntity);
    }

    @Override
    public boolean isCategoryExistsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public ContentPage<Category> findAllCategories(int page, int size, boolean isAsc) {
        Sort sort = isAsc ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageable);
        List<Category> categoryList = categoryEntityMapper.fromCategoryEntityToCategoryList(categoryEntityPage.getContent());
        return new ContentPage<>(
                categoryEntityPage.getTotalPages(),
                categoryEntityPage.getTotalElements(),
                categoryEntityPage.getPageable().getPageNumber(),
                categoryEntityPage.getPageable().getPageSize(),
                categoryEntityPage.isFirst(),
                categoryEntityPage.isLast(),
                categoryList
        );
    }

    @Override
    public boolean categoryExistsByUUID(UUID uuid) {
        return categoryRepository.existsById(uuid);
    }
}
