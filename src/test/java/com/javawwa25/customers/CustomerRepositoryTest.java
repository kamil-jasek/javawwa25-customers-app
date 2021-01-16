package com.javawwa25.customers;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void testFindCustomersByCity() {
        // given
        final var customer1 = new Person("Jan", "Nowak", "9282992992");
        customer1.addAddress(new Address("str1", "Warszawa", "01-300", "PL"));
        final var customer2 = new Company("Test S.A.", "928838383");
        customer2.addAddress(new Address("str2", "Kraków", "01-400", "PL"));
        final var customer3 = new Company("Testuj S.A.", "8290202020");
        customer3.addAddress(new Address("str3", "Warszawa", "02-340", "PL"));
        saveAll(customer1, customer2, customer3);

        // when
        final var customers = repository.findByCity("warszawa");

        // then
        assertEquals(2, customers.size());
        assertTrue(customers.containsAll(asList(customer1, customer3)));
    }

    @Test
    @Transactional
    void testCountByCity() {
        // given
        final var customer1 = new Person("Jan", "Nowak", "9282992992");
        customer1.addAddress(new Address("str1", "Warszawa", "01-300", "PL"));
        final var customer2 = new Company("Test S.A.", "928838383");
        customer2.addAddress(new Address("str2", "Kraków", "01-400", "PL"));
        final var customer3 = new Company("Testuj S.A.", "8290202020");
        customer3.addAddress(new Address("str3", "Warszawa", "02-340", "PL"));
        saveAll(customer1, customer2, customer3);

        // when
        final var results = repository.countByCity();

        // then
        assertEquals(2, results.size());
        assertArrayEquals(new Object[] {BigInteger.valueOf(1), "Kraków"}, results.get(0));
        assertArrayEquals(new Object[] { BigInteger.valueOf(2), "Warszawa" }, results.get(1));
    }

    private void saveAll(Customer ...customers) {
        repository.saveAll(asList(customers));
        em.flush();
        em.clear();
    }
}