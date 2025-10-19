package com.lian.marketing.lianstockmicroservice.domain.api.usecase;

import com.lian.marketing.lianstockmicroservice.domain.api.ICategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.ISubcategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.constants.ExceptionConstants;
import com.lian.marketing.lianstockmicroservice.domain.exception.CategoryWithIdNotExists;
import com.lian.marketing.lianstockmicroservice.domain.exception.SubcategoriesNotFoundException;
import com.lian.marketing.lianstockmicroservice.domain.exception.SubcategoryAlreadyExistsException;
import com.lian.marketing.lianstockmicroservice.domain.exception.SubcategoryNotFoundException;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;
import com.lian.marketing.lianstockmicroservice.domain.spi.ISubcategoryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class SubcategoryUseCase implements ISubcategoryServicePort {

    private final ISubcategoryPersistencePort subcategoryPersistencePort;
    private final ICategoryServicePort categoryServicePort;

    @Override
    public Subcategory createSubcategory(Subcategory subcategory) {
        if(subcategoryPersistencePort.isSubcategoryExist(subcategory.getName())) {
            throw new SubcategoryAlreadyExistsException(String.format(ExceptionConstants.SUBCATEGORY_ALREADY_EXISTS_WITH_NAME, subcategory.getName()));
        }
        if (!categoryServicePort.categoryExistsByUUID(subcategory.getCategory().getId())) {
            throw new CategoryWithIdNotExists(String.format(ExceptionConstants.CATEGORY_WITH_ID_NOT_EXISTS, subcategory.getCategory().getId()));
        }
        return subcategoryPersistencePort.saveSubcategory(subcategory);
    }

    @Override
    public ContentPage<Subcategory> findAllSubcategories(int page, int size, boolean isAsc, String sortBy) {
        ContentPage<Subcategory> content = subcategoryPersistencePort.findAllSubcategory(page, size, isAsc, sortBy);
        if(content.getContent().isEmpty()) {
            throw new SubcategoriesNotFoundException(ExceptionConstants.NO_RECORDS_FOR_SUBCATEGORIES);
        }
        return content;
    }

    @Override
    public ContentPage<Subcategory> findAllSubcategoriesByCategory(int page, int size, boolean isAsc, String sortBy, UUID categoryId) {
        ContentPage<Subcategory> content = subcategoryPersistencePort.findAllSubcategoryByCategory(page, size, isAsc, sortBy, categoryId);
        if(content.getContent().isEmpty()) {
            throw new SubcategoriesNotFoundException(ExceptionConstants.NO_RECORDS_FOR_SUBCATEGORIES);
        }
        return content;
    }

    @Override
    public void subcategoryExistsByUUID(UUID uuid) {
        if(!subcategoryPersistencePort.subcategoryExistsByUUID(uuid)) {
            throw new SubcategoryNotFoundException(String.format(ExceptionConstants.SUBCATEGORY_WITH_ID_NOT_EXISTS, uuid));
        }
    }

    @Override
    public Subcategory findSubcategoryById(UUID id) {
        Optional<Subcategory> subcategory = subcategoryPersistencePort.findSubcategoryById(id);
        if(subcategory.isEmpty()) {
            throw new SubcategoryNotFoundException(String.format(ExceptionConstants.SUBCATEGORY_WITH_ID_NOT_EXISTS, id));
        }
        return subcategory.get();
    }

    @Override
    public void updateSubcategory(Subcategory subcategory) {
        if(!subcategoryPersistencePort.subcategoryExistsByUUID(subcategory.getId())) {
            throw new SubcategoryNotFoundException(String.format(ExceptionConstants.SUBCATEGORY_WITH_ID_NOT_EXISTS, subcategory.getId()));
        }
        if(!categoryServicePort.categoryExistsByUUID(subcategory.getCategory().getId())){
            throw new CategoryWithIdNotExists(String.format(ExceptionConstants.CATEGORY_WITH_ID_NOT_EXISTS, subcategory.getCategory().getId()));
        }
        subcategory.setCategory(categoryServicePort.findCategoryById(subcategory.getCategory().getId()));
        subcategoryPersistencePort.updateSubcategory(subcategory);
    }

    @Override
    public void deleteSubcategoryById(UUID id) {
        if(!subcategoryPersistencePort.subcategoryExistsByUUID(id)) {
            throw new SubcategoryNotFoundException(String.format(ExceptionConstants.SUBCATEGORY_WITH_ID_NOT_EXISTS, id));
        }
        subcategoryPersistencePort.deleteSubcategoryById(id);
    }
}
