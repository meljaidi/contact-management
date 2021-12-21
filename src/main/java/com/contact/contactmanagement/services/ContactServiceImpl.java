package com.contact.contactmanagement.services;

import com.contact.contactmanagement.domain.Contact;
import com.contact.contactmanagement.domain.Status;
import com.contact.contactmanagement.repositories.ContactRepository;
import com.contact.contactmanagement.web.exception.ContactException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactServiceImpl  implements ContactService{

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact save(Contact contact) {
        if(contact.getStatus().equals(Status.FREELANCER) && StringUtils.isBlank(contact.getVatNumber())){
            throw new ContactException(HttpStatus.INSUFFICIENT_STORAGE, "Vat number is required  ");
        }
        return contactRepository.save(contact);
    }

    @Override
    public Optional<Contact> getContact(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact update(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }
}
