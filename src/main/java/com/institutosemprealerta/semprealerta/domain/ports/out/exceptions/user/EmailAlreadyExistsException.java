package com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.user;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
