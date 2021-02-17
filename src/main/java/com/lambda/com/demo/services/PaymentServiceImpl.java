package com.lambda.com.demo.services;


import com.lambda.com.demo.models.Payment;
import com.lambda.com.demo.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value= "paymentservices")
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentsRepository paymentsRepository;

    @Transactional
    @Override
    public Payment save(Payment payment) { return paymentsRepository.save(payment);}

}
