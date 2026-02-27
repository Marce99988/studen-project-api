package com.escuela.administracion_alumnos.Utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex,
                                                  HttpServletRequest request) {

            Map<String, List<String>> errors = new LinkedHashMap<>();
            ex.getBindingResult().getFieldErrors()
                    .forEach(e -> errors.computeIfAbsent(e.getField(), k -> new ArrayList<>())
                            .add(e.getDefaultMessage()));

            return ResponseEntity.badRequest().body(Map.of(
                    "status", 400,
                    "message", "Validación fallida",
                    "errors", errors,
                    "path", request.getRequestURI()
            ));
        }

        @ExceptionHandler(EmailAlreadyExistException.class)
        public ResponseEntity<?> handleEmailExists(EmailAlreadyExistException ex,
                                                   HttpServletRequest request) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "status", 409,
                    "message", ex.getMessage(),
                    "path", request.getRequestURI()
            ));
        }
}