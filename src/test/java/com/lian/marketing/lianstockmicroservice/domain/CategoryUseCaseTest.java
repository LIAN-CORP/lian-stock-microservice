package com.lian.marketing.lianstockmicroservice.domain;

import com.lian.marketing.lianstockmicroservice.domain.api.ICategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.usecase.CategoryUseCase;
import com.lian.marketing.lianstockmicroservice.domain.exception.CategoryAlreadyExistsException;
import com.lian.marketing.lianstockmicroservice.domain.mocks.DomainMocks;
import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private ICategoryServicePort categoryServicePort;
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryUseCase = new CategoryUseCase(categoryPersistencePort);
    }

    @Test
    void shouldSaveCategorySuccessfully() {
        //Arrange
        Category category = DomainMocks.mockNormalCategory();
        when(categoryPersistencePort.isCategoryExistsByName(category.getName())).thenReturn(false);

        //Act
        categoryServicePort.createCategory(category);

        //Assert
        assertDoesNotThrow(() -> categoryUseCase.createCategory(category));
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void shouldThrowExceptionWhenCategoryAlreadyExists() {
        //Arrange
        Category category = DomainMocks.mockNormalCategory();
        when(categoryPersistencePort.isCategoryExistsByName(category.getName())).thenReturn(true);

        //Act
        categoryServicePort.createCategory(category);

        //Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.createCategory(category));
        verify(categoryPersistencePort, times(0)).saveCategory(category);
    }

    @Test
    void shouldReturnCategoriesRecordsSuccessfully() {
        //Arrange
        ContentPage<Category> categoryPage = DomainMocks.mockCategoryPage();
        int page = 1, size = 1;
        boolean isAsc = true;
        when(categoryPersistencePort.findAllCategories(page, size, isAsc)).thenReturn(categoryPage);

        //Act
        ContentPage<Category> result = categoryUseCase.findAllCategories(page, size, isAsc);

        //Assert
        assertEquals(categoryPage, result);
        verify(categoryPersistencePort, times(1)).findAllCategories(page, size, isAsc);
    }
}
