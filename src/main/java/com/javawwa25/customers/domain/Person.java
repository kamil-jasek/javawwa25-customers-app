package com.javawwa25.customers.domain;

import static java.util.Objects.requireNonNull;

import com.javawwa25.customers.util.OnlyJpa;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PERSON")
final class Person extends Customer {

    private String firstName;
    private String lastName;
    private String pesel;

    @OnlyJpa
    private Person() {}

    Person(String firstName, String lastName, String pesel) {
        this.firstName = requireNonNull(firstName);
        this.lastName = requireNonNull(lastName);
        this.pesel = requireNonNull(pesel);
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
        if (!super.equals(o)) {
            return false;
        }
        Person person = (Person) o;
        return firstName.equals(person.firstName) && lastName.equals(person.lastName) && pesel.equals(person.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, pesel);
    }
}
