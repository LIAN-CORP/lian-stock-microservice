package com.lian.marketing.lianstockmicroservice.infrastructure.driving.http.exceptionhandler;

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
                LocalDateTime.now()
        ));
    }

}
