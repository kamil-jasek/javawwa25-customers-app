package com.javawwa25.customers.domain;

import com.javawwa25.customers.dto.CreatePersonDto;
import com.javawwa25.customers.dto.CustomerDto;
import com.javawwa25.customers.dto.PersonDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerFacade {

    private final CustomerService service;
    private final CustomerQuery query;

    CustomerFacade(CustomerService service, CustomerQuery query) {
        this.service = service;
        this.query = query;
    }

    public List<CustomerDto> findAll() {
        return query.findAll();
    }

    public PersonDto createPerson(CreatePersonDto dto) {
        return service.createPerson(dto);
    }


}
