package com.jaderhenryk.gestao_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    private MessageSource messsageSource;

    public ExceptionHandlerController(MessageSource messsageSource) {
        this.messsageSource = messsageSource;
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ErrorMessageDTO> dtoList = new ArrayList<>();
        exception.getBindingResult()
            .getFieldErrors()
            .forEach(err -> {
                String message = messsageSource.getMessage(err, LocaleContextHolder.getLocale());
                ErrorMessageDTO dto = new ErrorMessageDTO(message, err.getField());
                dtoList.add(dto);
            });

        return new ResponseEntity<>(dtoList, HttpStatus.BAD_REQUEST);
    }
}
