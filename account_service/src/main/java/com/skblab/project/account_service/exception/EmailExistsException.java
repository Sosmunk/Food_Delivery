package com.skblab.project.account_service.exception;

/**
 * <b>Исключение привязывания уже занятого Email-адреса к новому аккаунту</b>
 */
public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String message) {
        super(message);
    }
}
