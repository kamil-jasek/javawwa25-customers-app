package com.javawwa25.customers;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("COMPANY")
final class Company extends Customer {

    private String name;
    private String vatNumber;

    @OnlyJpa
    private Company() {}

    Company(String name, String vatNumber) {
        this.name = requireNonNull(name);
        this.vatNumber = requireNonNull(vatNumber);
    }

    public String getName() {
        return name;
    }

    public String getVatNumber() {
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
