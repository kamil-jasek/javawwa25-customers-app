package com.javawwa25.customers.domain;

import static java.util.Arrays.asList;

import java.util.UUID;
import javax.persistence.EntityManager;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

abstract class EntityTest {
    @Autowired
    protected CustomerRepository repository;

    @Autowired
    protected EntityManager em;

    protected void saveAll(Customer...customers) {
        repository.saveAll(asList(customers));
        em.flush();
        em.clear();
    }

    protected void save(Customer customer) {
        repository.save(customer);
        clearCache();
    }

    protected Customer readCustomer(UUID id) {
        return Hibernate.unproxy(repository.getOne(id), Customer.class);
    }

    protected void clearCache() {
        em.flush();
        em.clear();
    }
}
