package com.javawwa25.customers.api;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.javawwa25.customers.domain.CustomerFacade;
import com.javawwa25.customers.dto.CreatePersonDto;
import com.javawwa25.customers.dto.CustomerDto;
import com.javawwa25.customers.dto.CustomerDto.Type;
import com.javawwa25.customers.dto.PersonDto;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CustomerRestApi.class})
class CustomerRestApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerFacade facade;

    private ObjectMapper jsonMapper = new ObjectMapper()
        .registerModule(new ParameterNamesModule());

    @Test
    void testFilterPerson() throws Exception {
        // given
        given(facade.filterPerson(any())).willReturn(asList(
            new CustomerDto(UUID.randomUUID(),
                Type.COMPANY,
                "Test S.A.",
                "9390202003"),
            new CustomerDto(UUID.randomUUID(),
                Type.PERSON,
                "Jan Nowak",
                "83939939")
        ));

        // when
        mvc.perform(get("/api/v1/customers?firstName=Jan"))
        // then
            .andExpect(jsonPath("$.length()", equalTo(2)))
            .andExpect(jsonPath("$.[0].type", equalTo("COMPANY")))
            .andExpect(jsonPath("$.[0].name", equalTo("Test S.A.")))
            .andExpect(jsonPath("$.[0].taxNumber", equalTo("9390202003")))
            .andExpect(jsonPath("$.[1].type", equalTo("PERSON")))
            .andExpect(jsonPath("$.[1].name", equalTo("Jan Nowak")))
            .andExpect(jsonPath("$.[1].taxNumber", equalTo("83939939")))
            .andDo(print());
    }

    @Test
    void testFindAllWithObjectMapper() throws Exception {
        // given
        final var customers = asList(
            new CustomerDto(UUID.randomUUID(),
                Type.COMPANY,
                "Test S.A.",
                "9390202003"),
            new CustomerDto(UUID.randomUUID(),
                Type.PERSON,
                "Jan Nowak",
                "83939939")
        );
        given(facade.findAll()).willReturn(customers);

        // when
        final var result = mvc.perform(get("/api/v1/customers")).andReturn();
        final var resultCustomers = jsonMapper.readValue(result.getResponse().getContentAsString(),
            new TypeReference<List<CustomerDto>>() {});

        // then
        assertEquals(customers, resultCustomers);
    }

    @Test
    void testCreatePerson() throws Exception {
        // given
        given(facade.createPerson(any()))
            .willReturn(new PersonDto(UUID.randomUUID(), "Jan", "Nowak", "938388383"));

        // when
        mvc.perform(post("/api/v1/customers?type=person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequestContent(new CreatePersonDto("Jan", "Nowak", "938388383"))))
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.firstName", equalTo("Jan")))
            .andExpect(jsonPath("$.lastName", equalTo("Nowak")))
            .andExpect(jsonPath("$.pesel", equalTo("938388383")))
            .andDo(print());
    }

    private String createRequestContent(Object content) throws JsonProcessingException {
        return jsonMapper.writeValueAsString(content);
    }
}