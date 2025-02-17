package com.lian.marketing.lianstockmicroservice.domain.exception;

public class CategoriesNotFoundException extends RuntimeException {
    public CategoriesNotFoundException(String message) {
        super(message);
    }
}
