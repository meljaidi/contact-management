package com.contact.contactmanagement.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends AbstractEntity {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Enumerated
    private Status status;

    @ManyToMany
    @JoinTable(name = "contact_company",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Company> companies;

}
