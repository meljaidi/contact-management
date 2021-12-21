package com.contact.contactmanagement.web.exception;

import com.contact.contactmanagement.web.ContactController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackageClasses = ContactController.class)
public class RestExceptionsHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<String> handleException(Exception e) {
        if (e instanceof ContactException) {
            ContactException contactException = (ContactException) e;
            return new ResponseEntity<>(contactException.errorMessage(), contactException.getStatus());
        } else if (e instanceof CompanyException) {
            CompanyException companyException = (CompanyException) e;
            return new ResponseEntity<>(companyException.errorMessage(), companyException.getStatus());
        } else if (e instanceof DataIntegrityViolationException) {
            return new ResponseEntity<>("Another entity with the same identity exists", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Unexpected Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}