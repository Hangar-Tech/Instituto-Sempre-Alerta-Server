package com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
