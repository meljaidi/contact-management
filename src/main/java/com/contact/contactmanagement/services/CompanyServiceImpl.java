package com.contact.contactmanagement.services;

import com.contact.contactmanagement.domain.Company;
import com.contact.contactmanagement.domain.Contact;
import com.contact.contactmanagement.repositories.CompanyRepository;
import com.contact.contactmanagement.web.exception.ContactException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository enterpriseRepository;
    private final ContactService contactService;

    public CompanyServiceImpl(CompanyRepository enterpriseRepository, ContactService contactService) {
        this.enterpriseRepository = enterpriseRepository;
        this.contactService = contactService;
    }

    @Override
    public Company save(Company enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    @Override
    public Optional<Company> getEnterprise(Long id) {
        return enterpriseRepository.findById(id);
    }

    @Override
    public Company update(Company enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    @Override
    public List<Company> findAll() {
        return enterpriseRepository.findAll();
    }

    @Override
    public void delete(Company enterprise) {
        enterpriseRepository.delete(enterprise);
    }

    @Override
    public Optional<Company> findCompanyByVatAndVatNumber(String vatNumber) {
        return enterpriseRepository.findCompanyByVatNumber(vatNumber);
    }

    @Override
    public void addContactToCompany(Long CompanyID, Long contactID) {
        Optional<Company> company = getEnterprise(CompanyID);
        Optional<Contact> contact = contactService.getContact(contactID);
        if(company.isPresent() && contact.isPresent()) {
            Company newCompany = company.get();
            newCompany.getContacts().add(contact.get());
            save(newCompany);
        } else {
            throw new ContactException(HttpStatus.BAD_REQUEST, "cannot add contact to company ");
        }
    }

}
