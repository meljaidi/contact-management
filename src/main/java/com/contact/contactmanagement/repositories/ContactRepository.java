package com.contact.contactmanagement.repositories;

import com.contact.contactmanagement.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}