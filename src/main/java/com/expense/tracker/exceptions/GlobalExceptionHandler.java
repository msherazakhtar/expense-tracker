package com.expense.tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expense.tracker.dtos.ApiResponse;
import com.expense.tracker.enums.ResponseStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponse<String>> handleUserExists(UserAlreadyExistException exception) {
        System.out.println("Exception Occurred : " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ApiResponse<>(exception.getMessage(), "409", null, ResponseStatus.NOT_ALLOWED));
    }
 @ExceptionHandler(MailConfigurationNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleMailConfiguration(MailConfigurationNotFoundException exception) {
        System.out.println("Exception Occurred : " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ApiResponse<>(exception.getMessage(), "409", null, ResponseStatus.NOT_ALLOWED));
    }
}
