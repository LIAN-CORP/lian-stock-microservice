package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.adapter;

import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;
import com.lian.marketing.lianstockmicroservice.domain.spi.ISubcategoryPersistencePort;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity.SubcategoryEntity;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.mapper.ISubcategoryEntityMapper;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.repository.ISubcategoryRespository;
import com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.util.AdapterConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class SubcategoryAdapter implements ISubcategoryPersistencePort {

    private final ISubcategoryRespository subcategoryRepository;
    private final ISubcategoryEntityMapper subcategoryEntityMapper;

    @Override
    public Subcategory saveSubcategory(Subcategory subcategory) {
        SubcategoryEntity subcategoryEntity = subcategoryRepository.save(subcategoryEntityMapper.fromModelToSubcategoryEntity(subcategory));
        return subcategoryEntityMapper.fromEntityToSubcategoryModel(subcategoryEntity);
    }

    @Override
    public boolean isSubcategoryExist(String name) {
        return subcategoryRepository.existsByName(name);
    }

    @Override
    public ContentPage<Subcategory> findAllSubcategory(int page, int size, boolean isAsc, String sortBy) {
        Sort sort = isAsc
                ? Sort.by(AdapterConstants.getValueSortMappingSubcategory(sortBy)).ascending()
                : Sort.by(AdapterConstants.getValueSortMappingSubcategory(sortBy)).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<SubcategoryEntity> subcategoryPage = subcategoryRepository.findAll(pageable);
        List<Subcategory> subcategoryList = subcategoryEntityMapper.fromEntityToSubcategoryModelList(subcategoryPage.getContent());
        return new ContentPage<>(
                subcategoryPage.getTotalPages(),
                subcategoryPage.getTotalElements(),
                subcategoryPage.getPageable().getPageNumber(),
                subcategoryPage.getPageable().getPageSize(),
                subcategoryPage.isFirst(),
                subcategoryPage.isLast(),
                subcategoryList
        );
    }

    @Override
    public ContentPage<Subcategory> findAllSubcategoryByCategory(int page, int size, boolean isAsc, String sortBy, UUID categoryId) {
        Sort sort = isAsc
                ? Sort.by(AdapterConstants.getValueSortMappingSubcategory(sortBy)).ascending()
                : Sort.by(AdapterConstants.getValueSortMappingSubcategory(sortBy)).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<SubcategoryEntity> subcategoryPage = subcategoryRepository.findAllByCategory_Id(categoryId,pageable);
        List<Subcategory> subcategoryList = subcategoryEntityMapper.fromEntityToSubcategoryModelList(subcategoryPage.getContent());
        return new ContentPage<>(
                subcategoryPage.getTotalPages(),
                subcategoryPage.getTotalElements(),
                subcategoryPage.getPageable().getPageNumber(),
                subcategoryPage.getPageable().getPageSize(),
                subcategoryPage.isFirst(),
                subcategoryPage.isLast(),
                subcategoryList
        );
    }


    @Override
    public boolean subcategoryExistsByUUID(UUID uuid) {
        return subcategoryRepository.existsById(uuid);
    }

    @Override
    public Optional<Subcategory> findSubcategoryById(UUID id) {
        return subcategoryRepository.findById(id).map(subcategoryEntityMapper::fromEntityToSubcategoryModel);
    }
}
