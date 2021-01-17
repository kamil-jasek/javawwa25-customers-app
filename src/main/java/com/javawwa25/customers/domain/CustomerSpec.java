package com.javawwa25.customers.domain;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

final class CustomerSpec {

    private CustomerSpec() {}

    static class PersonFilter {
        private final String firstName;
        private final String lastName;
        private final String pesel;

        PersonFilter(String firstName, String lastName, String pesel) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.pesel = pesel;
        }
    }

    static Specification<Customer> withPersonFilter(PersonFilter filter) {
        return (root, query, cb) -> {

            // from Person p where ...
            // from Person p where 1=1
            var personRoot = cb.treat(root, Person.class);
            var predicate = cb.conjunction();

            // from Person p where 1=1 and p.firstName like :?
            if (StringUtils.hasText(filter.firstName)) {
                predicate = cb.and(predicate, cb.like(personRoot.get("firstName"), filter.firstName + "%"));
            }

            // form Person p where 1=1 and p.lastName like :?
            // from Person p where 1=1 and p.firstName like :? and p.lastName like :?
            if (StringUtils.hasText(filter.lastName)) {
                predicate = cb.and(predicate, cb.like(personRoot.get("lastName"), filter.lastName + "%"));
            }

            // from Person p where 1=1 and p.pesel like :?
            // from Person p where 1=1 and p.firstName like :? and p.pesel like :?
            // ....
            if (StringUtils.hasText(filter.pesel)) {
                predicate = cb.and(predicate, cb.like(personRoot.get("pesel"), filter.pesel + "%"));
            }

            return predicate;
        };
    }
}
