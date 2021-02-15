package com.lambda.com.demo.services;


import com.lambda.com.demo.models.Customer;
import com.lambda.com.demo.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value="customerservices")
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomersRepository customersRepository;

    @Transactional
    @Override
    public Customer save(Customer customer) {return customersRepository.save(customer) ;}
}
