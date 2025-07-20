package com.lian.marketing.lianstockmicroservice.domain.exception;

public class ProductIsNotInStockException extends RuntimeException {
    public ProductIsNotInStockException(String message) {
        super(message);
    }
}
