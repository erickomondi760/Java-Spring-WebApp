package com.user.web_app.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorResponse> showException(APIException e){
        APIException apiException = new APIException(e.getMessage(),e.getResponseCode());

        return ResponseEntity.status(e.getResponseCode()).body(new ErrorResponse(e.getResponseCode(),String.valueOf(e.getResponseCode())
                , Instant.now()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> showException(ResourceNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                e.getMessage(),Instant.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> returnErrors(MethodArgumentNotValidException e){
        Map<String,String> map = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(objectError -> {
            map.put(((FieldError)objectError).getField(),objectError.getDefaultMessage());
        });

        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> otherErrors(Exception e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),Instant.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
