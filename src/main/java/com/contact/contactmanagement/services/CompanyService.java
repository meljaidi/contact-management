package com.contact.contactmanagement.services;

import com.contact.contactmanagement.domain.Company;
import com.contact.contactmanagement.utils.SearchCriteria;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company save(Company Enterprise);
    Optional<Company> getEnterprise(Long id);
    Company update(Company Enterprise);
    List<Company> findAll();
    void delete(Company Enterprise);
    Optional<Company> findCompanyByVatAndVatNumber(String vatNumber);
    void addContactToCompany(Long CompanyID, Long contactID);
}
