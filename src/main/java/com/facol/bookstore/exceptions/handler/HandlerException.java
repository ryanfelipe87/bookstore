package com.facol.bookstore.exceptions.handler;

import com.facol.bookstore.exceptions.GenericNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GenericNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    protected ExceptionDetails handlerGenericException(GenericNotFoundException exception){
        return ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(NOT_FOUND.value())
                .title(NOT_FOUND.name())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }
}
