package com.javawwa25.customers.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.javawwa25.customers.dto.CreateCompanyDto;
import com.javawwa25.customers.dto.CreatePersonDto;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    @Test
    @Transactional
    void testCreatePerson() {
        // given
        final var dto = new CreatePersonDto("Jan", "Nowak", "82902002032");

        // when
        final var person = service.createPerson(dto);

        // then
        assertNotNull(person);
        assertNotNull(person.getId());
        assertEquals("Jan", person.getFirstName());
        assertEquals("Nowak", person.getLastName());
        assertEquals("82902002032", person.getPesel());
    }

    @Test
    @Transactional
    void testCreateCompany() {
        // given
        final var dto = new CreateCompanyDto("Test S.A.", "9284883293");

        // when
        final var company = service.createCompany(dto);

        // then
        assertNotNull(company);
        assertNotNull(company.getId());
        assertEquals("Test S.A.", company.getName());
        assertEquals("9284883293", company.getVatNumber());
    }
}