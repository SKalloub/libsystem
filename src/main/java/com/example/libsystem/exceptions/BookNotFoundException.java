package com.example.libsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)

public class BookNotFoundException extends RuntimeException{
    private int BookID;
    public BookNotFoundException(int BookID) {
        super("Book not found with ID of "+BookID);
        this.BookID = BookID;
    }
    public BookNotFoundException(String message, Throwable cause) {
        super(message,cause);
    }


    public BookNotFoundException(Throwable cause) {
        super(cause);
    }

}
