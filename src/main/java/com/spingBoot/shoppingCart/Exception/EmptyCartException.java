package com.spingBoot.shoppingCart.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestController
@RestControllerAdvice
public class EmptyCartException extends RuntimeException {
    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<ErrorApi> empty() {
        ErrorApi error = new ErrorApi();
        error.setError("EMPTY_CART");
        error.setDescription("Empty cart,no item found in cart");
        error.setDate(new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
