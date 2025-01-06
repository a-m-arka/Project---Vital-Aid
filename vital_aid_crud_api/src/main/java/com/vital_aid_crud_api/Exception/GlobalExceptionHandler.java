package com.vital_aid_crud_api.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vital_aid_crud_api.Util.ApiResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<String> handleDuplicateEntryException(DuplicateEntryException ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String messege = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(messege, false);
        return new ResponseEntity<>(apiResponse.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalPasswordException.class)
    public ResponseEntity<String> handleIllegalPasswordException(IllegalPasswordException ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArguementException(IllegalArgumentException ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(OtpExpirationException.class)
    public ResponseEntity<String> handleOtpExpirationException(OtpExpirationException ex) {
        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // Extract the first error message
        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .findFirst()
                .map(objectError -> ((FieldError) objectError).getDefaultMessage())
                .orElse("Validation error");

        // Return the error message directly as a response
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = "Validation error: " + ex.getMessage();
        ApiResponse response = new ApiResponse(errorMessage, false);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        ApiResponse response = new ApiResponse("Internal server error: " + ex.getMessage(), false);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}