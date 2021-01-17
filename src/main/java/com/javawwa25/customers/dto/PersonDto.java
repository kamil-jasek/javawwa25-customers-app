package com.javawwa25.customers.dto;

import java.util.Objects;
import java.util.UUID;

public final class PersonDto {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String pesel;

    public PersonDto(UUID id, String firstName, String lastName, String pesel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonDto personDto = (PersonDto) o;
        return id.equals(personDto.id) && firstName.equals(personDto.firstName) && lastName.equals(personDto.lastName)
            && pesel.equals(personDto.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, pesel);
    }
}
