package com.hotelbooking.hotelbooking.exceptions;

public class NotFoundDataException extends RuntimeException {
    public NotFoundDataException(String message) {
        super(message);
    }
}
