package com.lian.marketing.lianstockmicroservice.domain.exception;

public class PriceSellCannotBeLessThanPriceBuyException extends RuntimeException {
  public PriceSellCannotBeLessThanPriceBuyException(String message) {
    super(message);
  }
}
