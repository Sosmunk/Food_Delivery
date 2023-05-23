package com.skblab.project.account_service.exception;

/**
 * <b>Исключение привязывания уже занятого номера телефона к новому аккаунту</b>
 */
public class PhoneExistsException extends RuntimeException {
    public PhoneExistsException(String message) {
        super(message);
    }
}
