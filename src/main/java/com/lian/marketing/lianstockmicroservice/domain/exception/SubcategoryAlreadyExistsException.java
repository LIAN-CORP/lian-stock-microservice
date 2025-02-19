package com.lian.marketing.lianstockmicroservice.domain.exception;

public class SubcategoryAlreadyExistsException extends RuntimeException {
    public SubcategoryAlreadyExistsException(String message) {
        super(message);
    }
}
