package com.javawwa25.customers;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonTest {

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
        repository.save(person);
        em.flush();
        em.clear();

        // then
        final var readPerson = Hibernate.unproxy(repository.getOne(person.getId()), Customer.class);
        assertEquals(person, readPerson);
    }
}