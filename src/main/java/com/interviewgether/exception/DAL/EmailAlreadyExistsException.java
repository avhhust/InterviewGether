package com.interviewgether.exception.DAL;

public class EmailAlreadyExistsException extends UserAlreadyExistsException {

    public EmailAlreadyExistsException(String message, String causeFieldName) {
        super(message, causeFieldName);
    }
}
