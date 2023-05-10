package com.hotelbooking.hotelbooking.exceptions;

public class ExistedAccountException extends RuntimeException {
    public ExistedAccountException(String message) {
        super(message);
    }
}
