package com.map.nudonado.common.exception;

import com.map.nudonado.common.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationException(ApplicationException ex){
        log.info(String.format("Application Exception : %s", ex));
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorResponse(ex.getErrorCode(), ex.getMessage()));
    }
}
