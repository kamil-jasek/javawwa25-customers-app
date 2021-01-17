package com.javawwa25.customers.dto;

public class PersonFilterDto {

    private final String firstName;
    private final String lastName;
    private final String pesel;

    public PersonFilterDto(String firstName, String lastName, String pesel) {
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
}
