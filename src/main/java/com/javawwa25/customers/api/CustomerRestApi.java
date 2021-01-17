package com.javawwa25.customers.api;

import static java.util.Objects.requireNonNull;

import com.javawwa25.customers.domain.CustomerFacade;
import com.javawwa25.customers.dto.CustomerDto;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
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
}
