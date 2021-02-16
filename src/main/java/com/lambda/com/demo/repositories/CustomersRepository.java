package com.lambda.com.demo.repositories;

import com.lambda.com.demo.models.Customer;
import com.lambda.com.demo.views.OrderCounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByCustNameContainingIgnoringCase(String name);

    @Query(value="SELECT c.cust_name as name, count(ord_num) as countorder " +
                "FROM customers c LEFT JOIN orders o " +
                "ON c.cust_code = o.cust_code " +
                "GROUP BY c.cust_name " +
                "ORDER BY countorder DESC ", nativeQuery = true)
    List<OrderCounts>findCustomersOrderCount();
}
