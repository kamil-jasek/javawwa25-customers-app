package com.javawwa25.customers.api;

import static java.util.Objects.requireNonNull;

import com.javawwa25.customers.domain.CustomerFacade;
import com.javawwa25.customers.dto.CompanyDto;
import com.javawwa25.customers.dto.CreateCompanyDto;
import com.javawwa25.customers.dto.CreatePersonDto;
import com.javawwa25.customers.dto.CustomerDto;
import com.javawwa25.customers.dto.PersonDto;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
final class CustomerRestApi {

    private final CustomerFacade facade;

    CustomerRestApi(CustomerFacade facade) {
        this.facade = requireNonNull(facade);
    }

    @GetMapping
    List<CustomerDto> findAll() {
        return facade.findAll();
    }

    @PostMapping(params = { "person" })
    PersonDto createPerson(@RequestBody CreatePersonDto dto) {
        return facade.createPerson(dto);
    }

    @PostMapping(params = { "company" })
    CompanyDto createCompany(@RequestBody CreateCompanyDto dto) {
        return facade.createCompany(dto);
    }
}
