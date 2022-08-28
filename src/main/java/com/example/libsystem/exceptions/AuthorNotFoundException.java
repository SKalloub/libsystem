package com.example.libsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException{
    private int AuthorID;
    public AuthorNotFoundException(int authorID) {
        super(AuthorNotFoundException.class.getSimpleName()+" with ID of "+authorID);
        this.AuthorID = authorID;
    }
    public AuthorNotFoundException(String message, Throwable cause) {
        super(message,cause);
    }


    public AuthorNotFoundException(Throwable cause) {
        super(cause);
    }
}
