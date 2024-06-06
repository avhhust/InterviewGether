package com.interviewgether.exception.DAL;

public class UserAlreadyExistsException extends RuntimeException{
    private String message;
    private String causeFieldName;

    public UserAlreadyExistsException(String causeFieldName){
        this(
                causeFieldName.substring(0,1).toUpperCase() + causeFieldName.substring(1) + " already exists",
                causeFieldName
        );
    }

    public UserAlreadyExistsException(String message, String causeFieldName) {
        super(message);
        this.message = message;
        this.causeFieldName = causeFieldName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCauseFieldName() {
        return causeFieldName;
    }
}
