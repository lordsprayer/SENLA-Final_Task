package com.senla.courses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
//    @ExceptionHandler(DaoException.class)
//    public ResponseEntity<String> handlerDaoException(DaoException ex) {
//        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerDaoException(Exception ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}