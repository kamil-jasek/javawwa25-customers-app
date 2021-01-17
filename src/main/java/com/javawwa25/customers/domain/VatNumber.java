package com.javawwa25.customers.domain;

import com.javawwa25.customers.util.OnlyJpa;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
final class VatNumber {

    @Column(name = "vat_number")
    private String value;

    @OnlyJpa
    private VatNumber() {}

    VatNumber(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value == null || !value.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid vat number: " + value);
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VatNumber vatNumber = (VatNumber) o;
        return value.equals(vatNumber.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
