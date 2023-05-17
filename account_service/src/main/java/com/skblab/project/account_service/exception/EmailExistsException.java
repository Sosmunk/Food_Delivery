package com.skblab.project.account_service.exception;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String message) {
        super(message);
    }
}
