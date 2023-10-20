package com.trendyol.bootcampemployeemanagement.application;

import com.trendyol.bootcampemployeemanagement.domain.exception.EmployeeNotFoundException;
import com.trendyol.bootcampemployeemanagement.domain.exception.NotAuthorizedException;
import com.trendyol.bootcampemployeemanagement.interfaces.ApiError;
import com.trendyol.bootcampemployeemanagement.interfaces.Error;
import com.trendyol.bootcampemployeemanagement.interfaces.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Özgü bir exception tipi için handler
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ApiError> handleCustomException(EmployeeNotFoundException e) {
        final Error error = new Error();
        error.setErrorCode(ErrorCode.EMPLOYEE_NOT_FOUND.code());
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        final ApiError apiError = new ApiError(error);

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<String> handleNotAuthorizedException(NotAuthorizedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return errors;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_GATEWAY)
    public Map<Path, String> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        Map<Path, String> fieldException = new HashMap<>();
        constraintViolations.forEach(e -> {
            fieldException.put(e.getPropertyPath(), e.getMessage());
        });
        return fieldException;
    }

    // IllegalArgumentException tipi için handler
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Genel bir exception tipi için handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return new ResponseEntity<>("Unkown exception occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
