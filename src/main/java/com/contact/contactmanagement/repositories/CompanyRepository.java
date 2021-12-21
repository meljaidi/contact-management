package com.contact.contactmanagement.repositories;

import com.contact.contactmanagement.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findCompanyByVatNumber(String vatNumber);
}