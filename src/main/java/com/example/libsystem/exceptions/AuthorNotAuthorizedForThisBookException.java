package com.example.libsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)

public class AuthorNotAuthorizedForThisBookException extends RuntimeException{
    public AuthorNotAuthorizedForThisBookException(int authorID, int BookID) {

        super("Author with ID "+authorID+" is not authorized to access the book with id "+BookID);
    }
    public AuthorNotAuthorizedForThisBookException(String message, Throwable cause) {
        super(message,cause);
    }
    public AuthorNotAuthorizedForThisBookException(String message) {
        super(message);
    }
    public AuthorNotAuthorizedForThisBookException(Throwable cause) {
        super(cause);
    }
}
