package com.lian.marketing.lianstockmicroservice.domain;

import com.lian.marketing.lianstockmicroservice.domain.api.ISubcategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.usecase.SubcategoryUseCase;
import com.lian.marketing.lianstockmicroservice.domain.exception.SubcategoryAlreadyExistsException;
import com.lian.marketing.lianstockmicroservice.domain.mocks.DomainMocks;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;
import com.lian.marketing.lianstockmicroservice.domain.spi.ISubcategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SubcategoryUseCaseTest {

    @Mock
    private ISubcategoryServicePort subcategoryServicePort;
    @Mock
    private ISubcategoryPersistencePort subcategoryPersistencePort;
    private SubcategoryUseCase subcategoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subcategoryUseCase = new SubcategoryUseCase(subcategoryPersistencePort);
    }

    @Test
    void ShouldSaveSubcategorySuccessfully() {
        //Arrange
        Subcategory subcategory = DomainMocks.mockSubcategory();
        when(subcategoryPersistencePort.isSubcategoryExist(subcategory.getName())).thenReturn(false);

        //Act
        subcategoryServicePort.createSubcategory(subcategory);

        //Assert
        assertDoesNotThrow(() -> subcategoryUseCase.createSubcategory(subcategory));
        verify(subcategoryPersistencePort, times(1)).saveSubcategory(subcategory);
    }

    @Test
    void ShouldThrowExceptionWhenSubcategoryIsAlreadyExist() {
        //Arrange
        Subcategory subcategory = DomainMocks.mockSubcategory();
        when(subcategoryPersistencePort.isSubcategoryExist(subcategory.getName())).thenReturn(true);

        //Act
        subcategoryServicePort.createSubcategory(subcategory);

        //Assert
        assertThrows(SubcategoryAlreadyExistsException.class, () -> subcategoryUseCase.createSubcategory(subcategory));
        verify(subcategoryPersistencePort, times(0)).saveSubcategory(subcategory);
    }

}
