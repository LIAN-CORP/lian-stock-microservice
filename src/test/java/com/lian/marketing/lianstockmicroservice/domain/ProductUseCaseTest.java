package com.lian.marketing.lianstockmicroservice.domain;

import com.lian.marketing.lianstockmicroservice.domain.api.IProductServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.IS3ServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.ISubcategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.usecase.ProductUseCase;
import com.lian.marketing.lianstockmicroservice.domain.exception.ProductsNotFoundException;
import com.lian.marketing.lianstockmicroservice.domain.exception.SubcategoryNotFoundException;
import com.lian.marketing.lianstockmicroservice.domain.mocks.DomainMocks;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import com.lian.marketing.lianstockmicroservice.domain.spi.IProductPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductUseCaseTest {

    @Mock
    private IProductPersistencePort productPersistencePort;
    @Mock
    private IProductServicePort productServicePort;
    @Mock
    private ISubcategoryServicePort subcategoryServicePort;
    @Mock
    private IS3ServicePort s3ServicePort;
    private ProductUseCase productUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productUseCase = new ProductUseCase(productPersistencePort, subcategoryServicePort, s3ServicePort);
    }

    @Test
    void ShouldSaveProductSuccessfully() {
        //Arrange
        Product product = DomainMocks.mockProduct();
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "image.png",
                "image/jpeg",
                "contenido de imagen".getBytes()
        );
        String imagePath = "https://bucket.s3.amazonaws.com/image.jpg";

        when(s3ServicePort.saveImage(mockFile)).thenReturn(imagePath);

        //Act
        productServicePort.createProduct(product, mockFile);

        //Assert
        assertDoesNotThrow(() -> productUseCase.createProduct(product, mockFile));
        verify(subcategoryServicePort, times(1)).subcategoryExistsByUUID(product.getSubcategory().getId());
        verify(productPersistencePort, times(1)).saveProduct(product);
    }

    @Test
    void ShouldThrowExceptionWhenSubcategoryDoesNotExist() {
        //Arrange
        Product product = DomainMocks.mockProduct();
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "test-image.png",
                "image/jpeg",
                "contenido de imagen".getBytes()
        );
        doThrow(new SubcategoryNotFoundException(""))
                .when(subcategoryServicePort)
                .subcategoryExistsByUUID(product.getSubcategory().getId());

        String imagePath = "https://bucket.s3.amazonaws.com/image.jpg";

        when(s3ServicePort.saveImage(mockFile)).thenReturn(imagePath);

        //Act
        productServicePort.createProduct(product, mockFile);

        //Assert
        assertThrows(SubcategoryNotFoundException.class, () -> subcategoryServicePort.subcategoryExistsByUUID(product.getSubcategory().getId()));
        verify(subcategoryServicePort, times(1)).subcategoryExistsByUUID(product.getSubcategory().getId());
        verify(productPersistencePort, times(0)).saveProduct(product);
    }

    @Test
    void ShouldReturnProductsSuccessfully() {
        //Arrange
        int page = 0, size = 0;
        boolean isAsc = true;
        String sortBy = "name";
        ContentPage<Product> contentPage = DomainMocks.mockProductPageContent();
        when(productPersistencePort.findAllProducts(page, size, isAsc, sortBy)).thenReturn(contentPage);

        //Act
        ContentPage<Product> result = productUseCase.findAllProducts(page, size, isAsc, sortBy);

        //Assert
        assertEquals(contentPage, result);
        verify(productPersistencePort, times(1)).findAllProducts(page, size, isAsc, sortBy);
    }

    @Test
    void ShouldThrowExceptionWhenContentPageIsEmpty() {
        //Arrange
        int page = 0, size = 0;
        boolean isAsc = true;
        String sortBy = "name";
        ContentPage<Product> contentPage = DomainMocks.mockProductPageWithEmptyContent();
        when(productPersistencePort.findAllProducts(page, size, isAsc, sortBy)).thenReturn(contentPage);
        //Act
        productServicePort.findAllProducts(page, size, isAsc, sortBy);

        //Assert
        assertThrows(ProductsNotFoundException.class, () -> productUseCase.findAllProducts(page, size, isAsc, sortBy));
        verify(productPersistencePort, times(1)).findAllProducts(page, size, isAsc, sortBy);
    }

}
