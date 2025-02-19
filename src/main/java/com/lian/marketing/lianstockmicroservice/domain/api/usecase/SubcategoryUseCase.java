package com.lian.marketing.lianstockmicroservice.domain.api.usecase;

import com.lian.marketing.lianstockmicroservice.domain.api.ISubcategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.constants.ExceptionConstants;
import com.lian.marketing.lianstockmicroservice.domain.exception.SubcategoryAlreadyExistsException;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;
import com.lian.marketing.lianstockmicroservice.domain.spi.ISubcategoryPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubcategoryUseCase implements ISubcategoryServicePort {

    private final ISubcategoryPersistencePort subcategoryPersistencePort;

    @Override
    public void createSubcategory(Subcategory subcategory) {
        if(subcategoryPersistencePort.isSubcategoryExist(subcategory.getName())) {
            throw new SubcategoryAlreadyExistsException(String.format(ExceptionConstants.SUBCATEGORY_ALREADY_EXISTS_WITH_NAME, subcategory.getName()));
        }
        subcategoryPersistencePort.saveSubcategory(subcategory);
    }
}
