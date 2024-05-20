package com.happytails.springserver.controller;

import com.happytails.springserver.validation.UserAlreadyExsistException;
import com.happytails.springserver.validation.ValidationErrorResponse;
import com.happytails.springserver.validation.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(UserAlreadyExsistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  ValidationErrorResponse onUserAlreadyExistException(UserAlreadyExsistException e) {
        return new ValidationErrorResponse(List.of(new Violation("login", e.getMessage())));
    }
}
