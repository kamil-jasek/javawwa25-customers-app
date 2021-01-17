package com.javawwa25.customers.domain;

import static com.javawwa25.customers.domain.CustomerSpec.withPersonFilter;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import com.javawwa25.customers.dto.CompanyDto;
import com.javawwa25.customers.dto.CustomerDto;
import com.javawwa25.customers.dto.PersonDto;
import com.javawwa25.customers.dto.PersonFilterDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
final class CustomerQuery {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    CustomerQuery(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = requireNonNull(repository);
        this.mapper = requireNonNull(mapper);
    }

    List<CustomerDto> findAll() {
        return repository.findAll().stream()
            .map(mapper::map)
            .collect(toList());
    }

    List<CustomerDto> filterPerson(PersonFilterDto filterDto) {
        return repository.findAll(withPersonFilter(filterDto)).stream()
            .map(mapper::map)
            .collect(toList());
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
