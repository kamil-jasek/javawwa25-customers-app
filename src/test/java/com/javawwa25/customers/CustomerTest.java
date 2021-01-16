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

        //
        assertEquals(company, readCustomer(company.getId()));
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