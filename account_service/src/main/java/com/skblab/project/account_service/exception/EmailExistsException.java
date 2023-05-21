package com.skblab.project.account_service.exception;

/**
 * Исключение привязывания уже занятого Email-адреса к новому аккаунту
 */
public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String message) {
        super(message);
    }
}
