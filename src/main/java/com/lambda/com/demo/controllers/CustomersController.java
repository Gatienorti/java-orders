package com.lambda.com.demo.controllers;


import com.lambda.com.demo.models.Customer;
import com.lambda.com.demo.services.CustomerService;
import com.lambda.com.demo.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    //POST http://localhost:2019/customers/customer
    @PostMapping(value="/customer",consumes="application/json", produces="application/json")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer newCustomer){
        newCustomer.setCustcode(0L);
        newCustomer = customerService.save(newCustomer);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custcode}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(newCustomer,responseHeaders,HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/customers/customer/19
    @PutMapping(value="/restaurant/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateFullcustomer(@Valid @RequestBody Customer updateCustomer, @PathVariable long id){
        updateCustomer.setCustcode(id);
        updateCustomer = customerService.save(updateCustomer);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    // PATCH http://localhost:2019/customers/customer/19
    @PatchMapping(value="/customer/{id}", consumes="application/json")
    public ResponseEntity<?> updateCustomerId(@RequestBody Customer updateCustomer, @PathVariable long id){
        updateCustomer = customerService.update(updateCustomer, id);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    // DELETE http://localhost:2019/customers/customer/54
    @DeleteMapping(value="/customer/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long id){
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
