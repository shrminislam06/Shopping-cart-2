package com.spingBoot.shoppingCart.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestController
@RestControllerAdvice
public class ProductNotFoundException extends Exception {

    public ResponseEntity<ErrorApi> productNotFound() {
        ErrorApi er = new ErrorApi("NOT_FOUND", "Product not found with this id", new Date());
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }
}
