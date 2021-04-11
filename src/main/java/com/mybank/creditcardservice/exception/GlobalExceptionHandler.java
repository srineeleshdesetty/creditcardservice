package com.mybank.creditcardservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(x -> x.getField() + " " + x.getDefaultMessage()).collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<Object> handleBadRequestException(HttpServletRequest request, Exception ex) {
        log.info("Intercepted {} while processing {}", ex.getClass().toString(), request.getRequestURL());
        log.debug("Source exception", ex);
        return getErrorMessage(ex, HttpStatus.BAD_REQUEST);
    }
    
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler({ ServiceException.class })
    public ResponseEntity<Object> handleConflictException(HttpServletRequest request, Exception ex) {
        log.info("Intercepted {} while processing {}", ex.getClass().toString(), request.getRequestURL());
        log.debug("Source exception", ex);
        return getErrorMessage(ex, HttpStatus.CONFLICT);
    }

    private ResponseEntity<Object> getErrorMessage(Exception ex, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("errors", ex.getMessage());
        return new ResponseEntity<>(body, status);
    }
}
