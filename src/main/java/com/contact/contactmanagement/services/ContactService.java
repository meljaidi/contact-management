package com.contact.contactmanagement.services;

import com.contact.contactmanagement.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Contact save(Contact contact);
    Optional<Contact> getContact(Long id);
    Contact update(Contact contact);
    List<Contact> findAll();
    void delete(Contact contact);
}
