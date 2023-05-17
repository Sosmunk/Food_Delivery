package com.skblab.project.account_service.exception;

public class PhoneExistsException extends RuntimeException {
    public PhoneExistsException(String message) {
        super(message);
    }
}
