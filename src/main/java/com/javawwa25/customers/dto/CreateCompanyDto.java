package com.javawwa25.customers.dto;

import java.util.Objects;

public final class CreateCompanyDto {

    private final String name;
    private final String vatNumber;

    public CreateCompanyDto(String name, String vatNumber) {
        this.name = name;
        this.vatNumber = vatNumber;
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
        CreateCompanyDto that = (CreateCompanyDto) o;
        return name.equals(that.name) && vatNumber.equals(that.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, vatNumber);
    }
}
