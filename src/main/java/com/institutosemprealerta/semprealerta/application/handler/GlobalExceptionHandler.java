package com.institutosemprealerta.semprealerta.application.handler;

import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.ExceptionPattern;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.file.FileNotFoundException;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.file.InvalidFileException;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.post.PostNotFoundException;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.user.EmailAlreadyExistsException;
import com.institutosemprealerta.semprealerta.domain.ports.out.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionPattern> handlerUserNotFoundException(UserNotFoundException exception) {
        LocalDateTime timestamp = LocalDateTime.now();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionPattern.builder()
                        .title("User Not Found Exception")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details("User not found")
                        .timestamp(timestamp)
                        .developerMessage(exception.getClass().getName())
                        .build()
        );
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionPattern> handlerEmailAlreadyExistsException(EmailAlreadyExistsException bre) {
        LocalDateTime timestamp = LocalDateTime.now();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ExceptionPattern.builder()
                        .title("Email already exists, check the documentation")
                        .status(HttpStatus.CONFLICT.value())
                        .details(bre.getMessage())
                        .timestamp(timestamp)
                        .developerMessage(bre.getClass().getName())
                        .build()
        );
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ExceptionPattern> handlerPostNotFoundException(PostNotFoundException bre) {
        LocalDateTime timestamp = LocalDateTime.now();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionPattern.builder()
                        .title("Post not found, check the documentation")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(bre.getMessage())
                        .timestamp(timestamp)
                        .developerMessage(bre.getClass().getName())
                        .build()
        );
    }
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ExceptionPattern> handlerFileNotFoundException(FileNotFoundException bre) {
        LocalDateTime timestamp = LocalDateTime.now();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionPattern.builder()
                        .title("File not found, check the documentation")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(bre.getMessage())
                        .timestamp(timestamp)
                        .developerMessage(bre.getClass().getName())
                        .build()
        );
    }

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<ExceptionPattern> handlerInvalidFileException(InvalidFileException bre) {
        LocalDateTime timestamp = LocalDateTime.now();

        return ResponseEntity.badRequest().body(
                ExceptionPattern.builder()
                        .title("The file is invalid, check the documentation or try another file type")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(bre.getMessage())
                        .timestamp(timestamp)
                        .developerMessage(bre.getClass().getName())
                        .build()
        );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionPattern> handlerMethodArgumentNotValidExceptionException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();

        String fields = fieldError.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessage = fieldError.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        LocalDateTime timestamp = LocalDateTime.now();

        return ResponseEntity.badRequest().body(
                ExceptionPattern.builder()
                        .title("Bad Request Exception, Invalid Fields")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details("Check the field(s) error")
                        .timestamp(timestamp)
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldMessage)
                        .build()
        );
    }
}
