package com.example.student_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.student_api.entity.ErrorResponse;
import com.example.student_api.security.StudentNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlResponseEntity(StudentNotFoundException e)
    {
       ErrorResponse er = new ErrorResponse();
       er.setError(e.getMessage());
       er.setStatus(404);
       er.setCode("STUDENT_NOT_FOUND");
       er.setTimestamp("2026-06-13");
       return ResponseEntity.status(404).body(er);
    }
}
