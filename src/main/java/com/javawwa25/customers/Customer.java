package com.javawwa25.customers;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type")
abstract class Customer {

    @Id
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;

    protected Customer() {
        this.id = UUID.randomUUID();
        this.addresses = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void addAddress(Address address) {
        if (!addresses.contains(address)) {
            addresses.add(address);
        }
    }

    public boolean removeAddress(Address address) {
        return addresses.remove(address);
    }

    public List<Address> getAddresses() {
        return unmodifiableList(addresses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
