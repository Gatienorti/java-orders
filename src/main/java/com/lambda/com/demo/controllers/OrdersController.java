package com.lambda.com.demo.controllers;

import com.lambda.com.demo.models.Order;
import com.lambda.com.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    //POST http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?>addOrder(@Valid @RequestBody Order newOrder){
        newOrder.setOrdnum(0);
        newOrder = orderService.save(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ordnum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newOrderURI);
        return new ResponseEntity<>(newOrder, responseHeaders,HttpStatus.CREATED);
    }

    //PUT http://localhost:2019/orders/order/63
    @PutMapping(value = "/order/{id}", produces = "application/json", consumes ="application/json")
    public ResponseEntity<?> updateFullOrder(@Valid @RequestBody Order updateOrder, @PathVariable long id){
        updateOrder.setOrdnum(id);
        updateOrder = orderService.save(updateOrder);
        return  new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

    // DELETE http://localhost:2019/orders/order/58
    @DeleteMapping(value="/order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable long id){
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
