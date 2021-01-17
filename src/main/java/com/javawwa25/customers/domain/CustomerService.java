package com.javawwa25.customers.domain;

import static java.util.Objects.requireNonNull;

import com.javawwa25.customers.dto.CompanyDto;
import com.javawwa25.customers.dto.CreateCompanyDto;
import com.javawwa25.customers.dto.CreatePersonDto;
import com.javawwa25.customers.dto.PersonDto;
import com.javawwa25.customers.dto.UpdatePersonNameDto;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
class CustomerService {

    private final CustomerRepository repository;

    CustomerService(CustomerRepository repository) {
        this.repository = requireNonNull(repository);
    }

    @Transactional
    PersonDto createPerson(CreatePersonDto dto) {
        final var person = new Person(dto.getFirstName(),
            dto.getLastName(),
            dto.getPesel());
        repository.save(person);
        return new PersonDto(person.getId(),
            person.getFirstName(),
            person.getLastName(),
            person.getPesel());
    }

    @Transactional
    CompanyDto createCompany(CreateCompanyDto dto) {
        final var company = new Company(dto.getName(), new VatNumber(dto.getVatNumber()));
        repository.save(company);
        return new CompanyDto(company.getId(),
            company.getName(),
            company.getVatNumber().getValue());
    }

    @Transactional
    PersonDto updatePersonName(UpdatePersonNameDto dto) {
        final var updated = repository.updatePersonName(dto.getId(),
            dto.getFistName(),
            dto.getLastName());
        if (updated < 1) {
            throw new IllegalArgumentException("Person with id not found: " + dto.getId());
        }
        final var person = (Person) repository.getOneById(dto.getId());
        return new PersonDto(person.getId(),
            person.getFirstName(),
            person.getLastName(),
            person.getPesel());
    }
}
