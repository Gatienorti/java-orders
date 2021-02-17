package com.lambda.com.demo.services;

import com.lambda.com.demo.models.Customer;
import com.lambda.com.demo.views.OrderCounts;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);

    List<Customer> findAllOrders();

    Customer findCustomerById(long id);

    List<Customer> findCustomerByName(String name);

    List<OrderCounts> findCustomersOrderCount();

    void delete(long id);

    Customer update(Customer updateCustomer, long id);
}
