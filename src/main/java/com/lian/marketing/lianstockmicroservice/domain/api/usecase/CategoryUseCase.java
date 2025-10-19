package com.lian.marketing.lianstockmicroservice.domain.api.usecase;

import com.lian.marketing.lianstockmicroservice.domain.api.ICategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.constants.ExceptionConstants;
import com.lian.marketing.lianstockmicroservice.domain.exception.CategoriesNotFoundException;
import com.lian.marketing.lianstockmicroservice.domain.exception.CategoryAlreadyExistsException;
import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.spi.ICategoryPersistencePort;

import java.util.Optional;
import java.util.UUID;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Category createCategory(Category category) {
        if(categoryPersistencePort.isCategoryExistsByName(category.getName())) {
            throw new CategoryAlreadyExistsException(
                    String.format(ExceptionConstants.CATEGORY_ALREADY_EXISTS_WITH_NAME, category.getName()));
        }
        return categoryPersistencePort.saveCategory(category);
    }

    @Override
    public ContentPage<Category> findAllCategories(int page, int size, boolean isAsc) {
        ContentPage<Category> contentPage = categoryPersistencePort.findAllCategories(page, size, isAsc);
        if(contentPage.getContent().isEmpty()) {
            throw new CategoriesNotFoundException(ExceptionConstants.NO_RECORDS_FOR_CATEGORIES);
        }
        return contentPage;
    }

    @Override
    public boolean categoryExistsByUUID(UUID uuid) {
        return categoryPersistencePort.categoryExistsByUUID(uuid);
    }

    @Override
    public Category findCategoryById(UUID id) {
        Optional<Category> category = categoryPersistencePort.findCategoryById(id);
        if(category.isEmpty()){
            throw new CategoriesNotFoundException(ExceptionConstants.CATEGORY_WITH_ID_NOT_EXISTS);
        }
        return category.get();
    }

    @Override
    public void updateCategory(Category category) {
        if(!categoryPersistencePort.categoryExistsByUUID(category.getId())){
            throw new CategoriesNotFoundException(ExceptionConstants.CATEGORY_WITH_ID_NOT_EXISTS);
        }
        categoryPersistencePort.updateCategory(category);
    }

    @Override
    public void deleteCategoryById(UUID id) {
        if(!categoryPersistencePort.categoryExistsByUUID(id)){
            throw new CategoriesNotFoundException(ExceptionConstants.CATEGORY_WITH_ID_NOT_EXISTS);
        }
        categoryPersistencePort.deleteCategoryById(id);
    }

}
