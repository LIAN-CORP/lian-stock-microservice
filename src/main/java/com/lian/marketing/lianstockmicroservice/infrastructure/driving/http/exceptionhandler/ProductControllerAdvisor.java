package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.exceptionhandler;

import com.lian.marketing.lianstockmicroservice.domain.exception.PriceSellCannotBeLessThanPriceBuyException;
import com.lian.marketing.lianstockmicroservice.domain.exception.ProductIsNotInStockException;
import com.lian.marketing.lianstockmicroservice.domain.exception.ProductPriceSellDoNotMatchException;
import com.lian.marketing.lianstockmicroservice.domain.exception.ProductsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ProductControllerAdvisor {

    @ExceptionHandler(ProductsNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleProductsNotFoundException(ProductsNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                HttpStatus.NOT_FOUND.toString(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now(),
                "PRODUCTS_NOT_FOUND_EXCEPTION"
        ));
    }

    @ExceptionHandler(ProductIsNotInStockException.class)
    public ResponseEntity<ExceptionResponse> handleProductIsNotInStockException(ProductIsNotInStockException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now(),
                "PRODUCT_IS_NOT_IN_STOCK_EXCEPTION"
        ));
    }

    @ExceptionHandler(ProductPriceSellDoNotMatchException.class)
    public ResponseEntity<ExceptionResponse> handleProductPriceSellDoNotMatchException(ProductPriceSellDoNotMatchException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now(),
                "PRODUCT_PRICE_SELL_DO_NOT_MATCH_EXCEPTION"
        ));
    }

    @ExceptionHandler(PriceSellCannotBeLessThanPriceBuyException.class)
    public ResponseEntity<ExceptionResponse> handlePriceSellCannotBeLessThanPriceBuyException(PriceSellCannotBeLessThanPriceBuyException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now(),
                "PRICE_SELL_CANNOT_BE_LESS_THAN_PRICE_BUY_EXCEPTION"
        ));
    }
}
