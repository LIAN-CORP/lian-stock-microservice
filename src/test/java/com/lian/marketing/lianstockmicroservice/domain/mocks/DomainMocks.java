package com.lian.marketing.lianstockmicroservice.domain.mocks;

import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;
import com.lian.marketing.lianstockmicroservice.domain.model.Product;
import com.lian.marketing.lianstockmicroservice.domain.model.Subcategory;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DomainMocks {

    private DomainMocks() {}

    public static Category mockNormalCategory() {
        return new Category(
                UUID.randomUUID(),
                "Comida",
                "Todo lo referente a comida"
        );
    }

    public static List<Category> mockCategoriesList() {
        return List.of(mockNormalCategory());
    }

    public static ContentPage<Category> mockCategoryPage() {
        return new ContentPage<>(
            1,
                1,
                0,
                1,
                true,
                true,
                mockCategoriesList()
        );
    }

    public static List<Category> mockEmptyCategoriesList() {
        return Collections.emptyList();
    }

    public static ContentPage<Category> mockCategoryPageWithEmptyContent() {
        return new ContentPage<>(
                1,
                0,
                0,
                0,
                true,
                true,
                mockEmptyCategoriesList()
        );
    }

    public static Subcategory mockSubcategory() {
        return new Subcategory(
                UUID.randomUUID(),
                "Subcategory name",
                "Description",
                mockNormalCategory()
        );
    }

    public static List<Subcategory> mockEmptySubcategoryList() {
        return Collections.emptyList();
    }

    public static ContentPage<Subcategory> mockSubcategoryPageWithEmptyContent() {
        return new ContentPage<>(
                1,
                0,
                0,
                0,
                true,
                true,
                mockEmptySubcategoryList()
        );
    }

    public static List<Subcategory> mockSubcategoryList() {
        return List.of(mockSubcategory());
    }

    public static ContentPage<Subcategory> mockSubcategoryPageWithContent() {
        return new ContentPage<>(
                0,
                0,
                0,
                0,
                true,
                true,
                mockSubcategoryList()
        );
    }

    public static Product mockProduct() {
        return new Product(
            UUID.randomUUID(),
                "Arroz",
                "Description",
                1,
                100.0,
                100.0,
                mockSubcategory()
        );
    }

    public static List<Product> mockProductList() {
        return List.of(mockProduct());
    }

    public static ContentPage<Product> mockProductPageContent() {
        return new ContentPage<>(
                1,
                10,
                0,
                10,
                true,
                false,
                mockProductList()
        );
    }

    public static List<Product> mockEmptyProductList() {
        return Collections.emptyList();
    }

    public static ContentPage<Product> mockProductPageWithEmptyContent() {
        return new ContentPage<>(
                1,
                10,
                0,
                10,
                true,
                false,
                mockEmptyProductList()
        );
    }

}
