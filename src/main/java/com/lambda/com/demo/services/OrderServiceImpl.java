package com.lambda.com.demo.services;


import com.lambda.com.demo.models.Order;
import com.lambda.com.demo.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value= "orderservices")
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrdersRepository ordersRepository;

    @Transactional
    @Override
    public Order save(Order order){ return ordersRepository.save(order);}
}
