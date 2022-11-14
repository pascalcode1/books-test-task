package ru.pascalcode.bookstesttask.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler(value = {ValidateRequestException.class})
    public ResponseEntity<Object> handleValidateModelException(ValidateRequestException exception) {
        return new ResponseEntity<>(new ApiException(BAD_REQUEST, exception.getMessage()), BAD_REQUEST);
    }
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception exception) {
        return new ResponseEntity<>(new ApiException(INTERNAL_SERVER_ERROR, exception.getMessage()), BAD_REQUEST);
    }
}
