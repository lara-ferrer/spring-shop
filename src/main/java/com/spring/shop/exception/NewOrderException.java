package com.spring.shop.exception;

public class NewOrderException extends Exception {
    public NewOrderException(String message) {
        super(message);
    }

    public NewOrderException() {
        super();
    }
}
