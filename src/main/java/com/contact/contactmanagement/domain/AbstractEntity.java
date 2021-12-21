package com.contact.contactmanagement.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;
@Data
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String vatNumber;

    protected String address;

    @Column(name = "created_at", nullable = false)
    protected LocalDateTime createdAt;


    @Column(name = "modified_at", nullable = false)
    protected LocalDateTime modifiedAt;

    protected AbstractEntity(){
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;
        if (!Objects.equals(id, that.id)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
