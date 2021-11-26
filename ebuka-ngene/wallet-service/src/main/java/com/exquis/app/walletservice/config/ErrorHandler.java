package com.exquis.app.walletservice.config;

import com.exquis.app.walletservice.dto.ErrorResponse;
import com.exquis.app.walletservice.exception.HttpNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Chukwuebuka
 **/
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(value = { HttpNotFoundException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundExceptionHandler(HttpNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getLocalizedMessage());
    }
}
