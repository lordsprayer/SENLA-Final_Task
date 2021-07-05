package com.senla.courses.exception;

import com.senla.couses.api.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handlerIOException(IOException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handlerServiceException(ServiceException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(Exception ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}