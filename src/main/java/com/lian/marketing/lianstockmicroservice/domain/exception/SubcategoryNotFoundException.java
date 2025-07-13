package com.lian.marketing.lianstockmicroservice.domain.exception;

public class SubcategoryNotFoundException extends RuntimeException {
  public SubcategoryNotFoundException(String message) {
    super(message);
  }
}
