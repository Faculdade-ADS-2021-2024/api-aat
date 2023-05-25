package com.aatorganicos.aatorganicos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aatorganicos.aatorganicos.exception.RecordNotFoundException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApplicationControllerAdvace {
    
    @ExceptionHandler(RecordNotFoundException.class)
    public String  handleNotFoundException(RecordNotFoundException e) {
        return e.getMessage();
    }
}
