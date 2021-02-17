package com.lambda.com.demo.repositories;

import com.lambda.com.demo.models.Customer;
import com.lambda.com.demo.views.OrderCounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByCustnameContainingIgnoringCase(String name);

    @Query(value="SELECT c.custname as name, count(ordnum) as countorder " +
                "FROM customers c LEFT JOIN orders o " +
                "ON c.custcode = o.custcode " +
                "GROUP BY c.custname " +
                "ORDER BY countorder DESC ", nativeQuery = true)
    List<OrderCounts>findCustomersOrderCount();
}
