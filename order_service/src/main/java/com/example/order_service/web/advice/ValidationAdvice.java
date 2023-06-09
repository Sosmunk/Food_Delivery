package com.example.order_service.web.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
@Slf4j
public class ValidationAdvice extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        ArrayList<String> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(error ->{
            String message = error.getDefaultMessage();
            errors.add(message);
            log.error("Ошибки в полях заказа: {}", errors);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
