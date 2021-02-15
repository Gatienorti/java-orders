package com.lambda.com.demo.repositories;

import com.lambda.com.demo.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customer, Long> {
}
