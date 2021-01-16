package com.javawwa25.customers;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("from Customer c inner join c.addresses a where upper(a.city) = upper(?1)")
    List<Customer> findByCity(String city);
}
