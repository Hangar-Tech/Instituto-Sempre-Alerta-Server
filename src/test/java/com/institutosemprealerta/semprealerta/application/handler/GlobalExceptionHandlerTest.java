package com.institutosemprealerta.semprealerta.application.handler;

import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.ExceptionPattern;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.file.FileNotFoundException;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.file.InvalidFileException;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.post.PostNotFoundException;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.user.EmailAlreadyExistsException;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.user.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GlobalExceptionHandler")
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;


    @Test
    @DisplayName("Should handler with user not found exception")
    void should_HandlerUserNotFoundException() {
        UserNotFoundException exception = new UserNotFoundException("User not found");
        ResponseEntity<ExceptionPattern> response = globalExceptionHandler.handlerUserNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response);
        assertEquals(exception.getMessage(), Objects.requireNonNull(response.getBody()).getDetails());
        assertEquals(exception.getClass().getName(), Objects.requireNonNull(response.getBody()).getDeveloperMessage());
    }

    @Test
    @DisplayName("Should handler with email already exists exception")
    void should_HandlerEmailAlreadyExistsException() {
        EmailAlreadyExistsException exception = new EmailAlreadyExistsException("user email already exists");
        ResponseEntity<ExceptionPattern> response = globalExceptionHandler.handlerEmailAlreadyExistsException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(exception.getMessage(), Objects.requireNonNull(response.getBody()).getDetails());
        assertEquals(exception.getClass().getName(), Objects.requireNonNull(response.getBody()).getDeveloperMessage());
    }

    @Test
    @DisplayName("Should handler with post not found exception")
    void should_HandlerPostNotFoundException() {
        PostNotFoundException exception = new PostNotFoundException("Post not found");
        ResponseEntity<ExceptionPattern> response = globalExceptionHandler.handlerPostNotFoundException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(exception.getMessage(), Objects.requireNonNull(response.getBody()).getDetails());
        assertEquals(exception.getClass().getName(), Objects.requireNonNull(response.getBody()).getDeveloperMessage());
    }

    @Test
    @DisplayName("Should handler with file not found exception")
    void should_HandlerFileNotFoundException() {
        FileNotFoundException exception = new FileNotFoundException("File not found");
        ResponseEntity<ExceptionPattern> response = globalExceptionHandler.handlerFileNotFoundException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(exception.getMessage(), Objects.requireNonNull(response.getBody()).getDetails());
        assertEquals(exception.getClass().getName(), Objects.requireNonNull(response.getBody()).getDeveloperMessage());
    }

    @Test
    @DisplayName("Should handler with invalid file exception")
    void should_HandlerInvalidFileException() {
        InvalidFileException exception = new InvalidFileException("File type invalid");
        ResponseEntity<ExceptionPattern> response = globalExceptionHandler.handlerInvalidFileException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), Objects.requireNonNull(response.getBody()).getDetails());
        assertEquals(exception.getClass().getName(), Objects.requireNonNull(response.getBody()).getDeveloperMessage());
    }
}