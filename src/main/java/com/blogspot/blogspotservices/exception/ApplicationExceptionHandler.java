package com.blogspot.blogspotservices.exception;

import com.blogspot.blogspotservices.dto.ResponseApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError ->
                        errorMap.put(fieldError.getField(),
                                fieldError.getDefaultMessage()));
        ResponseApplication<Object>
                errors = ResponseApplication.builder().localDateTime(LocalDateTime.now())
                .result(errorMap).build();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> customAllError(Exception ex) {
        ResponseApplication<Object>
                errors = ResponseApplication.builder().localDateTime(LocalDateTime.now())
                .result(ex.getMessage()).build();
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> customUsernameNotFouns(UsernameNotFoundException ex) {
        ResponseApplication<Object>
                errors = ResponseApplication.builder().localDateTime(LocalDateTime.now())
                .result(ex.getMessage()).build();
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> customBadCredentialException(BadCredentialsException ex) {
        ResponseApplication<Object>
                errors = ResponseApplication.builder().localDateTime(LocalDateTime.now())
                .result(ex.getMessage()).build();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Object> customDisabledException(DisabledException ex) {
        ResponseApplication<Object>
                errors = ResponseApplication.builder().localDateTime(LocalDateTime.now())
                .result(ex.getMessage()).build();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> customBusinessError(BusinessException ex) {
        ResponseApplication<Object>
                errors = ResponseApplication.builder().localDateTime(LocalDateTime.now())
                .result(ex.getMessage()).build();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
