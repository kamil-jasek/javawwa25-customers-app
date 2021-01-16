package com.javawwa25.customers;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("from Customer c inner join c.addresses a where upper(a.city) = upper(?1)")
    List<Customer> findByCity(String city);

    @Query(value = "select count(c.id), a.city  from customers c "
        + "inner join addresses a on a.customer_id = c.id "
        + "group by a.city",
        nativeQuery = true)
    List<Object[]> countByCity();

    @Query(value = "select count(c.id) as count, a.city  from customers c "
        + "inner join addresses a on a.customer_id = c.id "
        + "group by a.city",
        nativeQuery = true)
    List<CountByCity> countByCityWithType();

    interface CountByCity {
        int getCount();
        String getCity();
    }

    @Modifying
    @Query("update Person p set p.firstName = :firstName, p.lastName = :lastName where p.id = :id")
    int updatePersonName(UUID id, String firstName, String lastName);
}
