package com.example.libsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedMoveException extends RuntimeException {
    public UnAuthorizedMoveException() {
        super(UnAuthorizedMoveException.class.getSimpleName());
    }
    public UnAuthorizedMoveException(String message, Throwable cause) {
        super(message,cause);
    }


    public UnAuthorizedMoveException(Throwable cause) {
        super(cause);
    }
}

