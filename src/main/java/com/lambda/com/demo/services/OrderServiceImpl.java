package com.lambda.com.demo.services;


import com.lambda.com.demo.models.Customer;
import com.lambda.com.demo.models.Order;
import com.lambda.com.demo.models.Payment;
import com.lambda.com.demo.repositories.CustomersRepository;
import com.lambda.com.demo.repositories.OrdersRepository;
import com.lambda.com.demo.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value= "orderservices")
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private PaymentsRepository paymentsRepository;

    @Transactional
    @Override
    public Order save(Order order){
        Order newOrder = new Order();

        if(order.getOrdnum() != 0){
            ordersRepository.findById(order.getOrdnum())
                    .orElseThrow(()-> new EntityNotFoundException("Order number : "+order.getOrdnum()+ " not found!!"));
            newOrder.setOrdnum(order.getOrdnum());
        }
        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setCustomer(order.getCustomer());
//        newOrder.setCustomer(customersRepository.findById(order.getCustomer().getCustcode())
//            .orElseThrow(()-> new EntityNotFoundException("do not find customer!!!")));

        newOrder.getPayments().clear();
        for(Payment p : order.getPayments()){
            Payment newPay= paymentsRepository.findById(p.getPaymentid())
                    .orElseThrow(()-> new EntityNotFoundException("Payment "+ p.getPaymentid()+ " not found!!"));
            newOrder.getPayments().add(newPay);

        }

        return ordersRepository.save(newOrder);}

    @Override
    public Order findOrderByNumber(long id) {
        return ordersRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Sorry order number: "+id+" not found!"));
    }

    @Override
    public void delete(long id) {
        if(ordersRepository.findById(id).isPresent()){
            ordersRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Order "+id+" not found!!");
        }
    }
}
