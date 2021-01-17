package com.javawwa25.customers.domain;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import com.javawwa25.customers.dto.CustomerDto;
import com.javawwa25.customers.dto.CustomerDto.Type;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerQueryTest extends EntityTest {

    @Autowired
    private CustomerQuery query;

    @Test
    @Transactional
    void testFindPersonById() {
        // given
        final var personExample = new Person("Jan", "Nowak", "9393030030");
        save(personExample);

        // when
        final var personOpt = query.findPersonById(personExample.getId());

        // then
        assertNotNull(personOpt);
        assertTrue(personOpt.isPresent());
        final var person = personOpt.get();
        assertEquals(personExample.getId(), person.getId());
    }

    @Test
    @Transactional
    void testFindAll() {
        // given
        final var company = new Company("Test S.A.", VatNumber.of("9394384838"));
        final var person = new Person("Jan", "Kowalski", "938848393");
        saveAll(company, person);

        // when
        final var customers = query.findAll();

        // then
        assertEquals(2, customers.size());
        assertEquals(asList(
            new CustomerDto(company.getId(),
                Type.COMPANY,
                company.getName(),
                company.getVatNumber().getValue()),
            new CustomerDto(person.getId(),
                Type.PERSON,
                person.getFullName(),
                person.getPesel())
        ), customers);
    }
}