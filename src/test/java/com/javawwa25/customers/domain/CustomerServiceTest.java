package com.javawwa25.customers.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.javawwa25.customers.dto.CreatePersonDto;
import com.javawwa25.customers.dto.PersonDto;
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
}