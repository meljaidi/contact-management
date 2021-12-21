package com.contact.contactmanagement.web;

import com.contact.contactmanagement.domain.Contact;
import com.contact.contactmanagement.services.ContactService;
import com.contact.contactmanagement.web.exception.ContactException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/contacts")
    public List<Contact> allContacts() {
        List<Contact> contacts = contactService.findAll();
        return contacts;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/contacts")
    public Contact create(@Validated @RequestBody Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            throw new ContactException(HttpStatus.BAD_REQUEST, "Cannot save entity ");
        }
        return contactService.save(contact);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/contacts/{id}")
    public Contact get(@PathVariable Long id) {
        Optional<Contact> contact;
        contact = contactService.getContact(id);
        if (contact.isPresent()) {
            return contact.get();
        } else {
            throw new ContactException(HttpStatus.NOT_FOUND, "Unable to find element with id " + id);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/contacts/{id}")
    public void update(@Validated @RequestBody Contact updatedContact, @PathVariable Long id, BindingResult result) {
        Optional<Contact> contact = contactService.getContact(id);
        if (contact.isPresent()) {
            Contact newContact = contact.get();
            newContact.setFirstName(updatedContact.getFirstName());
            newContact.setLastName(updatedContact.getLastName());
            newContact.setAddress(updatedContact.getAddress());
            newContact.setStatus(updatedContact.getStatus());
            contactService.save(newContact);
        } else {
            throw new ContactException(HttpStatus.NOT_FOUND, "element not found " + id);
        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/contacts/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Contact> contact = contactService.getContact(id);
        contact.ifPresent(value -> contactService.delete(value));
    }
}
