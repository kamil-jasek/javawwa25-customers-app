package com.javawwa25.customers.domain;

import static java.util.Objects.requireNonNull;

import com.javawwa25.customers.dto.CompanyDto;
import com.javawwa25.customers.dto.PersonDto;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
final class CustomerQuery {

    private final CustomerRepository repository;

    CustomerQuery(CustomerRepository repository) {
        this.repository = requireNonNull(repository);
    }

    Optional<PersonDto> findPersonById(UUID id) {
        return repository.findPersonById(id).map(person -> new PersonDto(person.getId(),
            person.getFirstName(),
            person.getLastName(),
            person.getPesel()));
    }

    Optional<CompanyDto> findCompanyById(UUID id) {
        return repository.findCompanyById(id).map(company -> new CompanyDto(company.getId(),
            company.getName(),
            company.getVatNumber().getValue()));
    }
}
