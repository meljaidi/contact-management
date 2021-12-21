package com.contact.contactmanagement.web.exception;

import org.springframework.http.HttpStatus;

public class ContactException extends RuntimeException{
    private HttpStatus status;

    public ContactException(String message) {
        super(message);
    }

    public ContactException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public ContactException(HttpStatus status,Throwable cause) {
        super(cause);
        this.status = status;
    }

    public ContactException(String message, Throwable cause) {
        super(message, cause);
    }

    public String errorMessage(){
        return status.value() + ":".concat(getMessage());
    }

    public HttpStatus getStatus() {
        return status;
    }
}