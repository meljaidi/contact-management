package com.contact.contactmanagement.web;

import com.contact.contactmanagement.domain.Company;
import com.contact.contactmanagement.services.CompanyService;
import com.contact.contactmanagement.services.ContactService;
import com.contact.contactmanagement.web.exception.CompanyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CompanyController {

    private final CompanyService companyService;
    private final ContactService contactService;

    public CompanyController(CompanyService companyService, ContactService contactService) {
        this.companyService = companyService;
        this.contactService = contactService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/companies")
    public List<Company> getAllEnterprises() {
        List<Company> companies = companyService.findAll();
        return companies;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/companies/{id}")
    public Company getEnterprise(@PathVariable Long id) {
        Optional<Company> enterprise = companyService.getEnterprise(id);
        if (enterprise.isPresent()) {
            return enterprise.get();
        } else {
            throw new CompanyException(HttpStatus.NOT_FOUND, "company not found " + id);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/companies")
    public Company create(@RequestBody Company enterprise, BindingResult result) {
        if (result.hasErrors()) {
            throw new CompanyException(HttpStatus.BAD_REQUEST, "Cannot save company ");
        }
        return companyService.save(enterprise);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/companies/{id}")
    public void update(@RequestBody Company updatedEnterprise, @PathVariable Long id) {
        Optional<Company> enterprise = companyService.getEnterprise(id);
        if (enterprise.isPresent()) {
            Company newEnterprise = enterprise.get();
            newEnterprise.setVatNumber(updatedEnterprise.getVatNumber());
            newEnterprise.setAddress(updatedEnterprise.getAddress());
        } else {
            throw new CompanyException(HttpStatus.NOT_FOUND, "Company not found ");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/companies/{companyID}/{contactID}")
    public void addContactToAEnterprise(@PathVariable Long companyID, @PathVariable Long contactID) {
        companyService.addContactToCompany(companyID, contactID);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/companies/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Company> enterprise = companyService.getEnterprise(id);
        enterprise.ifPresent(value -> companyService.delete(value));
    }

    //TODO just an example
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/search/{vatNumber}")
    public Optional<Company> getCompayByVatNumber(@PathVariable String vatNumber) {
        return companyService.findCompanyByVatAndVatNumber(vatNumber);
    }


}
