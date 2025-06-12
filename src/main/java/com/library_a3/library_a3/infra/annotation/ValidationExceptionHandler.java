package com.library_a3.library_a3.infra.annotation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> onValidationError(MethodArgumentNotValidException ex) {
        Map<String,String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fe ->
                erros.put(fe.getField(), fe.getDefaultMessage())
        );
        return ResponseEntity
                .badRequest()
                .body(erros);
    }
}
