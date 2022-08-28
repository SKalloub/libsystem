package com.example.libsystem.exceptions;

public class WrongUserNameOrPasswordException extends RuntimeException {
    public WrongUserNameOrPasswordException() {
        super(WrongUserNameOrPasswordException.class.getSimpleName());
    }
    public WrongUserNameOrPasswordException(String message, Throwable cause) {
        super(message,cause);
    }


    public WrongUserNameOrPasswordException(Throwable cause) {
        super(cause);
    }
}
