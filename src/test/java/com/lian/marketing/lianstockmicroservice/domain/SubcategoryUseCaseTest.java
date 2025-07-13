package com.lian.marketing.lianstockmicroservice.domain;

import com.lian.marketing.lianstockmicroservice.domain.api.ICategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.ISubcategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.usecase.SubcategoryUseCase;
import com.lian.marketing.lianstockmicroservice.domain.exception.CategoryWithIdNotExists;
import com.lian.marketing.lianstockmicroservice.domain.exception.SubcategoriesNotFoundException;
import com.lian.marketing.lianstockmicroservice.domain.exception.SubcategoryAlreadyExistsException;
import com.lian.marketing.lianstockmicroservice.domain.exception.SubcategoryNotFoundException;
import com.lian.marketing.lianstockmicroservice.domain.mocks.DomainMocks;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;
import com.lian.marketing.lianstockmicroservice.domain.spi.ISubcategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubcategoryUseCaseTest {

    @Mock
    private ISubcategoryServicePort subcategoryServicePort;
    @Mock
    private ISubcategoryPersistencePort subcategoryPersistencePort;
    @Mock
    private ICategoryServicePort categoryServicePort;
    private SubcategoryUseCase subcategoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subcategoryUseCase = new SubcategoryUseCase(subcategoryPersistencePort, categoryServicePort);
    }

    @Test
    void ShouldSaveSubcategorySuccessfully() {
        //Arrange
        Subcategory subcategory = DomainMocks.mockSubcategory();
        when(subcategoryPersistencePort.isSubcategoryExist(subcategory.getName())).thenReturn(false);
        when(categoryServicePort.categoryExistsByUUID(subcategory.getCategory().getId())).thenReturn(true);

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

    @Test
    void shouldThrowExceptionWhenCategoryNotExistByUUID() {
        //Assert
        Subcategory subcategory = DomainMocks.mockSubcategory();
        when(subcategoryPersistencePort.isSubcategoryExist(subcategory.getName())).thenReturn(false);
        when(categoryServicePort.categoryExistsByUUID(subcategory.getCategory().getId())).thenReturn(false);

        //Act
        subcategoryServicePort.createSubcategory(subcategory);

        //Assert
        assertThrows(CategoryWithIdNotExists.class, () -> subcategoryUseCase.createSubcategory(subcategory));
        verify(subcategoryPersistencePort, times(1)).isSubcategoryExist(subcategory.getName());
        verify(categoryServicePort, times(1)).categoryExistsByUUID(subcategory.getCategory().getId());
        verify(subcategoryPersistencePort, times(0)).saveSubcategory(subcategory);
    }

    @Test
    void ShouldThrowExceptionWhenSubcategoryHaveNoRecords() {
        //Arrange
        int page = 0, size = 10;
        boolean isAsc = true;
        String sortBy = "name";
        ContentPage<Subcategory> content = DomainMocks.mockSubcategoryPageWithEmptyContent();

        when(subcategoryPersistencePort.findAllSubcategory(page, size, isAsc, sortBy)).thenReturn(content);

        //Act
        subcategoryServicePort.findAllSubcategories(page, size, isAsc, sortBy);

        //Assert
        assertThrows(SubcategoriesNotFoundException.class, () -> subcategoryUseCase.findAllSubcategories(page, size, isAsc, sortBy));
        verify(subcategoryPersistencePort, times(1)).findAllSubcategory(page, size, isAsc, sortBy);
    }

    @Test
    void shouldReturnSubcategoriesSuccessfully() {
        //Arrange
        int page = 0, size = 10;
        boolean isAsc = true;
        String sortBy = "name";
        ContentPage<Subcategory> content = DomainMocks.mockSubcategoryPageWithContent();

        when(subcategoryPersistencePort.findAllSubcategory(page, size, isAsc, sortBy)).thenReturn(content);

        //Act
        ContentPage<Subcategory> result = subcategoryUseCase.findAllSubcategories(page, size, isAsc, sortBy);

        //Assert
        assertEquals(content, result);
        verify(subcategoryPersistencePort, times(1)).findAllSubcategory(page, size, isAsc, sortBy);
    }

    @Test
    void shouldThrowExceptionWhenSubcategoryNotFoundByUUID() {
        //Arrange
        Subcategory subcategory = DomainMocks.mockSubcategory();
        when(subcategoryPersistencePort.subcategoryExistsByUUID(subcategory.getCategory().getId())).thenReturn(false);

        //Act
        subcategoryServicePort.subcategoryExistsByUUID(subcategory.getCategory().getId());

        //Assert
        assertThrows(SubcategoryNotFoundException.class, () -> subcategoryUseCase.subcategoryExistsByUUID(subcategory.getCategory().getId()));
        verify(subcategoryPersistencePort, times(1)).subcategoryExistsByUUID(subcategory.getCategory().getId());
    }

    @Test
    void shouldNotThrowExceptionWhenSubcategoryIsFoundByUUID() {
        //Arrange
        Subcategory subcategory = DomainMocks.mockSubcategory();
        when(subcategoryPersistencePort.subcategoryExistsByUUID(subcategory.getCategory().getId())).thenReturn(true);

        //Act
        subcategoryServicePort.subcategoryExistsByUUID(subcategory.getCategory().getId());

        //Assert
        assertDoesNotThrow(() -> subcategoryUseCase.subcategoryExistsByUUID(subcategory.getCategory().getId()));
        verify(subcategoryPersistencePort, times(1)).subcategoryExistsByUUID(subcategory.getCategory().getId());
    }

}
