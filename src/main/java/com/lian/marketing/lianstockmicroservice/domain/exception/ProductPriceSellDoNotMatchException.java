package com.lian.marketing.lianstockmicroservice.domain.exception;

public class ProductPriceSellDoNotMatchException extends RuntimeException {
    public ProductPriceSellDoNotMatchException(String message) {
        super(message);
    }
}
