package com.spring.shop.exception;

public class UserRegistrationException extends Exception {

    public UserRegistrationException() {
        super();
    }

    public UserRegistrationException(String message) {
        super(message);
    }
}
