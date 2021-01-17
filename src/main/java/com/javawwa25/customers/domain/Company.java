package com.javawwa25.customers.domain;

import static java.util.Objects.requireNonNull;

import com.javawwa25.customers.util.OnlyJpa;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("COMPANY")
final class Company extends Customer {

    private String name;

    @Embedded
    private VatNumber vatNumber;

    @OnlyJpa
    private Company() {}

    Company(String name, VatNumber vatNumber) {
        this.name = requireNonNull(name);
        this.vatNumber = requireNonNull(vatNumber);
    }

    public String getName() {
        return name;
    }

    public VatNumber getVatNumber() {
        return vatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Company company = (Company) o;
        return name.equals(company.name) && vatNumber.equals(company.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, vatNumber);
    }
}
