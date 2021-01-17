package com.javawwa25.customers.domain;

import com.javawwa25.customers.dto.PersonFilterDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

final class CustomerSpec {

    private CustomerSpec() {}

    static Specification<Customer> withPersonFilter(PersonFilterDto filter) {
        return (root, query, cb) -> {

            // from Person p where ...
            // from Person p where 1=1
            var personRoot = cb.treat(root, Person.class);
            var predicate = cb.conjunction();

            // from Person p where 1=1 and p.firstName like :?
            if (StringUtils.hasText(filter.getFirstName())) {
                predicate = cb.and(predicate, cb.like(personRoot.get("firstName"), filter.getFirstName() + "%"));
            }

            // form Person p where 1=1 and p.lastName like :?
            // from Person p where 1=1 and p.firstName like :? and p.lastName like :?
            if (StringUtils.hasText(filter.getLastName())) {
                predicate = cb.and(predicate, cb.like(personRoot.get("lastName"), filter.getLastName() + "%"));
            }

            // from Person p where 1=1 and p.pesel like :?
            // from Person p where 1=1 and p.firstName like :? and p.pesel like :?
            // ....
            if (StringUtils.hasText(filter.getPesel())) {
                predicate = cb.and(predicate, cb.like(personRoot.get("pesel"), filter.getPesel() + "%"));
            }

            return predicate;
        };
    }
}
