package com.lian.marketing.lianstockmicroservice.domain.mocks;

import com.lian.marketing.lianstockmicroservice.domain.model.Category;
import com.lian.marketing.lianstockmicroservice.domain.model.ContentPage;

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

}
