package com.javatraining.moksiakova.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseException> handleException(EntityNotFoundException e) {
        String message = String.format("%s %s", e.getMessage());
        ResponseException response = new ResponseException(message);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
