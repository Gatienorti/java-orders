package com.lambda.com.demo.services;


import com.lambda.com.demo.models.Customer;
import com.lambda.com.demo.repositories.CustomersRepository;
import com.lambda.com.demo.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value="customerservices")
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomersRepository customersRepository;

    @Transactional
    @Override
    public Customer save(Customer customer) {return customersRepository.save(customer) ;}

    @Override
    public List<Customer> findAllOrders() {
        List<Customer> list= new ArrayList<>();
        customersRepository.findAll().iterator().forEachRemaining(list::add);
    return list;
    }

    @Override
    public Customer findCustomerById(long id) {
        return customersRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Customer "+ id+" not found!!"));
    }

    @Override
    public List<Customer> findCustomerByName(String name) {
        return customersRepository.findByCustnameContainingIgnoringCase(name);
    }

    @Override
    public List<OrderCounts> findCustomersOrderCount() {
        return customersRepository.findCustomersOrderCount();
    }
}
