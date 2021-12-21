package com.contact.contactmanagement.web.exception;

import org.springframework.http.HttpStatus;

public class CompanyException extends RuntimeException {
    private HttpStatus status;

    public CompanyException(String message) {
        super(message);
    }

    public CompanyException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public CompanyException(HttpStatus status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    public CompanyException(String message, Throwable cause) {
        super(message, cause);
    }

    public String errorMessage() {
        return status.value() + ":".concat(getMessage());
    }

    public HttpStatus getStatus() {
        return status;
    }
}