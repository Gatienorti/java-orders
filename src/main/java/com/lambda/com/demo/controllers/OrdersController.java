package com.lambda.com.demo.controllers;

import com.lambda.com.demo.models.Order;
import com.lambda.com.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/orders")
public class OrdersController {
    @Autowired
    private OrderService orderService;
    //http://localhost:2019/orders/order/7
    @GetMapping(value ="/order/{id}", produces = "application/json")
    public ResponseEntity<?> findOrderByNumber(@PathVariable long id){
        Order o = orderService.findOrderByNumber(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
