package com.lambda.com.demo.repositories;

import com.lambda.com.demo.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Order, Long> {

}
