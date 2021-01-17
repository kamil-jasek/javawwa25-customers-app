package com.javawwa25.customers.dto;

import java.util.Objects;
import java.util.UUID;

public final class UpdatePersonNameDto {

    private final UUID id;
    private final String fistName;
    private final String lastName;

    public UpdatePersonNameDto(UUID id, String fistName, String lastName) {
        this.id = id;
        this.fistName = fistName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getFistName() {
        return fistName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdatePersonNameDto that = (UpdatePersonNameDto) o;
        return id.equals(that.id) && fistName.equals(that.fistName) && lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fistName, lastName);
    }
}
