package ru.example.construct.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> sorry(Exception ex) {
        return new ResponseEntity<>("Some mistake, sorry", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
