package com.javawwa25.customers.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Objects;
import java.util.UUID;

public final class CustomerDto {

    public enum Type {
        COMPANY, PERSON;
    }

    private final UUID id;
    private final Type type;
    private final String name;
    private final String taxNumber;

    public CustomerDto(UUID id, Type type, String name, String taxNumber) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.taxNumber = taxNumber;
    }

    public UUID getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(id, that.id) && type == that.type && Objects.equals(name, that.name)
            && Objects.equals(taxNumber, that.taxNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, taxNumber);
    }
}
