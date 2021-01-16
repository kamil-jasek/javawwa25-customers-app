package com.javawwa25.customers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerTest {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void testCreatePerson() {
        // given
        final var person = new Person("Jan", "Nowak", "90282939393");

        // when
        save(person);

        // then
        assertEquals(person, readCustomer(person.getId()));
    }

    @Test
    @Transactional
    void testCreateCompany() {
        // given
        final var company = new Company("Test S.A.", "938493993");

        // when
        save(company);

        // then
        assertEquals(company, readCustomer(company.getId()));
    }

    @Test
    @Transactional
    void testAddAddress() {
        // given
        final var customer = new Company("Test S.A.", "929030202");
        final var address = new Address("str", "wawa", "01-200", "PL");
        customer.addAddress(address);

        // when
        save(customer);

        // then
        final var readCustomer = readCustomer(customer.getId());
        assertEquals(1, readCustomer.getAddresses().size());
        assertTrue(readCustomer.getAddresses().contains(address));
    }

    private void save(Customer customer) {
        repository.save(customer);
        clearCache();
    }

    private Customer readCustomer(UUID id) {
        return Hibernate.unproxy(repository.getOne(id), Customer.class);
    }

    private void clearCache() {
        em.flush();
        em.clear();
    }
}