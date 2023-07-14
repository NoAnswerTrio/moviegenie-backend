package com.moviegenie.exception;

import com.moviegenie.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MovieGenieAppException.class)
    public ResponseEntity<?> applicationHandler(MovieGenieAppException e) {

        log.error("error occurs : {}", e.getMessage());

        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(Response.error(e.getErrorCode().toString()));
    }
}
