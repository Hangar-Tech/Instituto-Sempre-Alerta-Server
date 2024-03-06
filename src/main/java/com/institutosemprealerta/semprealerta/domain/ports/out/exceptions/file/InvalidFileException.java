package com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.file;

public class InvalidFileException extends RuntimeException {
    public InvalidFileException(String message) {
        super(message);
    }
}
