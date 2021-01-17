package com.javawwa25.customers.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.javawwa25.customers.dto.PersonDto;
import java.util.Optional;
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
}