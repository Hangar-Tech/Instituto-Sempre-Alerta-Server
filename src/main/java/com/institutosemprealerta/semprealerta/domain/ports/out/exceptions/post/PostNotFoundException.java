package com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.post;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
