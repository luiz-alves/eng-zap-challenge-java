package br.com.olx.challenge.api.handler;

import br.com.olx.challenge.common.exception.PropertyLoaderException;
import br.com.olx.challenge.common.exception.UnloadedDatabaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class CustomErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PropertyLoaderException.class)
    public ResponseEntity<ApiError> propertyLoaderException(PropertyLoaderException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(ex.getErrorCode()));
    }

    @ExceptionHandler(UnloadedDatabaseException.class)
    public ResponseEntity<ApiError> unloadedDatabaseException(UnloadedDatabaseException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(ex.getErrorCode()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> illegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(ex.getMessage()));
    }
}
