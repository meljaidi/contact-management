package com.contact.contactmanagement;

import com.contact.contactmanagement.domain.Company;
import com.contact.contactmanagement.domain.Contact;
import com.contact.contactmanagement.domain.Status;
import com.contact.contactmanagement.repositories.CompanyRepository;
import com.contact.contactmanagement.repositories.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ContactManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner create(ContactRepository contactRepository, CompanyRepository enterpriseRepository) {

        return args -> {
            /**
             * init data
             */
            Company company = new Company();
            company.setVatNumber("BCE01");
            company.setAddress("ADDR1");

            Company company2 = new Company();
            company2.setVatNumber("BCE02");
            company2.setAddress("ADDR2");

            Contact employee = new Contact();
            employee.setStatus(Status.EMPLOYEE);
            employee.setAddress("ContactAddr1");
            employee.setLastName("lastname1");
            employee.setFirstName("firstname1");

            Contact freelancer = new Contact();
            freelancer.setStatus(Status.FREELANCER);
            freelancer.setAddress("ContactAddr2");
            freelancer.setLastName("lastname2");
            freelancer.setFirstName("firstname2");
            freelancer.setVatNumber("BCE02");


            Contact savedEmployee = contactRepository.save(employee);
            Contact savedFreelancer = contactRepository.save(freelancer);


            company.setContacts(List.of(savedEmployee, savedFreelancer));
            company2.setContacts(List.of(freelancer));


            Company savedCompany = enterpriseRepository.save(company);
            Company savedcompany2 = enterpriseRepository.save(company2);



        };
    }

}
