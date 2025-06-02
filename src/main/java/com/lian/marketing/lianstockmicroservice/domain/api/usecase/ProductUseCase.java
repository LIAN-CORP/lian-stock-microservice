package com.lian.marketing.lianstockmicroservice.domain.api.usecase;

import com.lian.marketing.lianstockmicroservice.domain.api.IProductServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.IS3ServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.ISubcategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.constants.ExceptionConstants;
import com.lian.marketing.lianstockmicroservice.domain.exception.ProductsNotFoundException;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import com.lian.marketing.lianstockmicroservice.domain.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
public class ProductUseCase implements IProductServicePort {

    private final IProductPersistencePort productPersistencePort;
    private final ISubcategoryServicePort subcategoryServicePort;
    private final IS3ServicePort s3ServicePort;

    @Override
    public void createProduct(Product product, MultipartFile file) {
        subcategoryServicePort.subcategoryExistsByUUID(product.getSubcategory().getId());
        if(file != null && !file.isEmpty()) {
            String imagePath = s3ServicePort.saveImage(file);
            product.setImagePath(imagePath);
        }
            productPersistencePort.saveProduct(product);
    }

    @Override
    public ContentPage<Product> findAllProducts(int page, int size, boolean isAsc, String sortBy) {
        ContentPage<Product> contentPage = productPersistencePort.findAllProducts(page, size, isAsc, sortBy);
        if(contentPage.getContent().isEmpty()) {
            throw new ProductsNotFoundException(ExceptionConstants.NO_RECORDS_FOR_PRODUCTS);
        }
        return contentPage;
    }
}
