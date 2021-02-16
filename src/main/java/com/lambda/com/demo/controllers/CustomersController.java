package com.lambda.com.demo.controllers;


import com.lambda.com.demo.models.Customer;
import com.lambda.com.demo.services.CustomerService;
import com.lambda.com.demo.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {
    @Autowired
    private CustomerService customerService;

    //http://localhost:2019/customers/orders
    @GetMapping(value="/orders", produces = "application/json")
    public ResponseEntity<?> listAllOrders(){
        List<Customer> rtnList = customerService.findAllOrders();

        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //http://localhost:2019/customers/customer/7
    @GetMapping(value="/customer/{id}", produces="application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long id) {
        Customer c = customerService.findCustomerById(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
    //http://localhost:2019/customers/namelike/mes
    @GetMapping(value="/namelike/{name}", produces="application/json")
    public ResponseEntity<?>findCustomerByName(@PathVariable String name){
        List<Customer> rtnList = customerService.findCustomerByName(name);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }
    //http://localhost:2019/customers/orders/count
    @GetMapping(value="/orders/count", produces="application/json")
    public ResponseEntity<?> findCustomersOrderCount(){
        List<OrderCounts> rtnList = customerService.findCustomersOrderCount();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }
}
