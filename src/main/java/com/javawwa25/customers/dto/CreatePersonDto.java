package com.javawwa25.customers.dto;

import java.util.Objects;

public final class CreatePersonDto {

    private final String firstName;
    private final String lastName;
    private final String pesel;

    public CreatePersonDto(String firstName, String lastName, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
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
        CreatePersonDto that = (CreatePersonDto) o;
        return firstName.equals(that.firstName) && lastName.equals(that.lastName) && pesel.equals(that.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, pesel);
    }
}
