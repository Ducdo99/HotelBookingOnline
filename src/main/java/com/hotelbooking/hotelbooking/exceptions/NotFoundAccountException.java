package com.hotelbooking.hotelbooking.exceptions;

public class NotFoundAccountException extends RuntimeException {
    public NotFoundAccountException(String message) {
        super(message);
    }
}
