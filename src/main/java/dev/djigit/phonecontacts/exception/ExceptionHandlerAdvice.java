package dev.djigit.phonecontacts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler({ContactAlreadyExistsException.class, ContactNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleContactAlreadyExistsException(RuntimeException e) {
        return e.getMessage();
    }

    @ExceptionHandler(PermissionDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String handlePermissionDeniedException(PermissionDeniedException e) {
        return e.getMessage();
    }

}
