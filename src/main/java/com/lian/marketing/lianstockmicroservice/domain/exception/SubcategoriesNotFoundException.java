package com.lian.marketing.lianstockmicroservice.domain.exception;

public class SubcategoriesNotFoundException extends RuntimeException {
    public SubcategoriesNotFoundException(String message) {
        super(message);
    }
}
