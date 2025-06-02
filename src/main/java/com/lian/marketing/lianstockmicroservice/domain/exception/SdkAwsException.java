package com.lian.marketing.lianstockmicroservice.domain.exception;

public class SdkAwsException extends RuntimeException {
    public SdkAwsException(String message) {
        super(message);
    }
}
