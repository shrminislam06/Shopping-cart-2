package com.spingBoot.shoppingCart.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestController
@RestControllerAdvice
public class UserNotFoundException extends Exception {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorApi> userNotFind() {
        ErrorApi error = new ErrorApi("NOT_FOUND", "User not found with this username", new Date());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
