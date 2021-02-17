package com.lambda.com.demo.repositories;

import com.lambda.com.demo.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepository extends CrudRepository<Payment, Long> {
}
