package com.lian.marketing.lianstockmicroservice.domain.api.usecase;

import com.lian.marketing.lianstockmicroservice.domain.api.IProductServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.IS3ServicePort;
import com.lian.marketing.lianstockmicroservice.domain.api.ISubcategoryServicePort;
import com.lian.marketing.lianstockmicroservice.domain.constants.ExceptionConstants;
import com.lian.marketing.lianstockmicroservice.domain.exception.ProductIsNotInStockException;
import com.lian.marketing.lianstockmicroservice.domain.exception.ProductsNotFoundException;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import com.lian.marketing.lianstockmicroservice.domain.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public void discountProductsInStock(List<Product> products) {
        products = mergeItemsProductsWithSimilarId(products);
        products.forEach(product -> {
            if(!productPersistencePort.productExistsByUUID(product.getId())){
                throw new ProductsNotFoundException(ExceptionConstants.PRODUCT_NOT_FOUND);
            }
            if(!productPersistencePort.isProductInStock(product.getId(), product.getStock())){
                throw new ProductIsNotInStockException(ExceptionConstants.PRODUCT_IS_NOT_IN_STOCK);
            }
        });
        products.forEach(product -> productPersistencePort.discountProductInStock(product.getId(), product.getStock()));
    }

    @Override
    public Product getProductById(UUID id) {
        Optional<Product> product = productPersistencePort.findProductById(id);
        if(product.isEmpty()) {
            throw new ProductsNotFoundException(ExceptionConstants.PRODUCT_NOT_FOUND);
        }
        return product.get();
    }

    @Override
    public void updateProduct(Product product) {
        if(!productPersistencePort.productExistsByUUID(product.getId())){
            throw new ProductsNotFoundException(ExceptionConstants.PRODUCT_NOT_FOUND);
        }
        product.setSubcategory(subcategoryServicePort.findSubcategoryById(product.getSubcategory().getId()));
        Optional<Product> p = productPersistencePort.findProductById(product.getId());
        if(p.isEmpty()) {
            throw new ProductsNotFoundException(ExceptionConstants.PRODUCT_NOT_FOUND);
        }
        product.setImagePath(p.get().getImagePath());
        productPersistencePort.updateProduct(product);
    }

    private List<Product> mergeItemsProductsWithSimilarId(List<Product> products){
        return new ArrayList<>(products.stream().collect(Collectors.toMap(
                Product::getId,
                p -> new Product(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getStock(),
                        p.getPriceSell(),
                        p.getPriceBuy(),
                        p.getSubcategory(),
                        p.getImagePath()
                ),
                (p1, p2) -> new Product(p1.getId(), p1.getName(), p1.getDescription(), p1.getStock() + p2.getStock(), p1.getPriceSell(), p1.getPriceBuy(), p1.getSubcategory(), p1.getImagePath())
        )).values());
    }
}
