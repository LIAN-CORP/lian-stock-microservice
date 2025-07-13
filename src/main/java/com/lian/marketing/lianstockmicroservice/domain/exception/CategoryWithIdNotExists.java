package com.lian.marketing.lianstockmicroservice.domain.exception;

public class CategoryWithIdNotExists extends RuntimeException {
    public CategoryWithIdNotExists(String message) {
        super(message);
    }
}
