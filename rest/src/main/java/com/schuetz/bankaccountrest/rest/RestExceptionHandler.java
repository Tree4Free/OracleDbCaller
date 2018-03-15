package com.schuetz.bankaccountrest.rest;

import com.schuetz.bankaccountrest.CustomExceptions.Exceptions.BaseException;
import com.schuetz.commons.domain.dto.responses.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
        log.error("Error",ex);
        return ResponseEntity.status(400).body(ErrorResponse.builder()
                .errorCode(ex.getErrorCode())
                .exceptionMessage(ex.getMessage())
                .errorMessage(ex.getErrorMessage())
                .build());
    }

}
