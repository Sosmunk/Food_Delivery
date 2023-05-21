package com.skblab.project.account_service.exception;

/**
 * Исключение привязывания уже занятого номера телефона к новому аккаунту
 */
public class PhoneExistsException extends RuntimeException {
    public PhoneExistsException(String message) {
        super(message);
    }
}
