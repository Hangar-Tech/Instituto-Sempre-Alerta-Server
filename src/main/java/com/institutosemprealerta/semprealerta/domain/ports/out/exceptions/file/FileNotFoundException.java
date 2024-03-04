package com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.file;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message) {
        super(message);
    }
}
