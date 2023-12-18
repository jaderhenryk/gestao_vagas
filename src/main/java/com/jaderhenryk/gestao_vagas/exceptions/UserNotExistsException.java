package com.jaderhenryk.gestao_vagas.exceptions;

public class UserNotExistsException extends RuntimeException  {
    
    public UserNotExistsException() {
        super("User not exists.");
    }
}
