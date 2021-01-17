package com.javawwa25.customers.dto;

import java.util.Objects;
import java.util.UUID;

public final class CompanyDto {

    private final UUID id;
    private final String name;
    private final String vatNumber;

    public CompanyDto(UUID id, String name, String vatNumber) {
        this.id = id;
        this.name = name;
        this.vatNumber = vatNumber;
    }

    public UUID getId() {
        return id;
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
        CompanyDto that = (CompanyDto) o;
        return id.equals(that.id) && name.equals(that.name) && vatNumber.equals(that.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vatNumber);
    }
}
