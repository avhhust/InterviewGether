package com.interviewgether.exception.DAL;

public class UsernameAlreadyExistsException extends UserAlreadyExistsException{
    public UsernameAlreadyExistsException(String message, String causeFieldName) {
        super(message, causeFieldName);
    }
}
