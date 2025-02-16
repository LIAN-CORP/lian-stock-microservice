package com.lian.marketing.lianstockmicroservice.domain.mocks;

import com.lian.marketing.lianstockmicroservice.domain.model.Category;

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

}
