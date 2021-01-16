package com.javawwa25.customers;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
