package com.javawwa25.customers.domain;

import com.javawwa25.customers.dto.CustomerDto;
import com.javawwa25.customers.dto.CustomerDto.Type;
import org.springframework.stereotype.Component;

@Component
final class CustomerMapper {

    CustomerDto map(Customer customer) {
        if (customer instanceof Company) {
            final var company = (Company) customer;
            return new CustomerDto(company.getId(),
                Type.COMPANY,
                company.getName(),
                company.getVatNumber().getValue());
        } else if (customer instanceof Person) {
            final var person = (Person) customer;
            return new CustomerDto(person.getId(),
                Type.PERSON,
                person.getFullName(),
                person.getPesel());
        }
        throw new IllegalStateException("customer mapping not found for class: " + customer.getClass().getName());
    }
}
