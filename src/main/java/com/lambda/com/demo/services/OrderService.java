package com.lambda.com.demo.services;

import com.lambda.com.demo.models.Order;

public interface OrderService {
    Order save(Order order);

    Order findOrderByNumber(long id);

    void delete(long id);
}
