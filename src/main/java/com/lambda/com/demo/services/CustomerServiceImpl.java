package com.lambda.com.demo.services;


import com.lambda.com.demo.models.Agent;
import com.lambda.com.demo.models.Customer;
import com.lambda.com.demo.models.Order;
import com.lambda.com.demo.repositories.AgentsRepository;
import com.lambda.com.demo.repositories.CustomersRepository;
import com.lambda.com.demo.repositories.OrdersRepository;
import com.lambda.com.demo.repositories.PaymentsRepository;
import com.lambda.com.demo.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value="customerservices")
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private AgentsRepository agentsRepository;

    @Override
    public List<Customer> findAllOrders() {
        List<Customer> list= new ArrayList<>();
        customersRepository.findAll().iterator().forEachRemaining(list::add);
    return list;
    }

    @Override
    public Customer findCustomerById(long id) {
        return customersRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Customer "+ id+" not found!!"));
    }

    @Override
    public List<Customer> findCustomerByName(String name) {
        return customersRepository.findByCustnameContainingIgnoringCase(name);
    }

    @Override
    public List<OrderCounts> findCustomersOrderCount() {
        return customersRepository.findCustomersOrderCount();
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();
    if(customer.getCustcode() != 0) {
        customersRepository.findById(customer.getCustcode())
                .orElseThrow(()-> new EntityNotFoundException("customer "+ customer.getCustcode()+ " not found!!"));
        newCustomer.setCustcode(customer.getCustcode());
    }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        newCustomer.getOrders().clear();
        for(Order o: customer.getOrders()){
            Order newOrder = new Order();
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setOrderdescription(o.getOrderdescription());
            newOrder.setOrdnum(o.getOrdnum());
            newOrder.setPayments(o.getPayments());
            newOrder.setCustomer(newCustomer);

            newCustomer.getOrders().add(newOrder);
        }

    return customersRepository.save(newCustomer) ;}

    @Transactional
    @Override
    public Customer update(Customer updateCustomer, long id) {
        Customer currentCustomer = customersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("customer" + id + " not found!!"));

        if (updateCustomer.getCustname() != null) currentCustomer.setCustname(updateCustomer.getCustname());
        if (updateCustomer.getCustcity() != null) currentCustomer.setCustcity(updateCustomer.getCustcity());
        if (updateCustomer.getWorkingarea() != null) currentCustomer.setWorkingarea(updateCustomer.getWorkingarea());
        if (updateCustomer.getCustcountry() != null) currentCustomer.setCustcountry(updateCustomer.getCustcountry());
        if (updateCustomer.getGrade() != null) currentCustomer.setGrade(updateCustomer.getGrade());
        if (updateCustomer.getOpeningamt() != 0.00) currentCustomer.setOpeningamt(updateCustomer.getOpeningamt());
        if (updateCustomer.getReceiveamt() != 0.00) currentCustomer.setReceiveamt(updateCustomer.getReceiveamt());
        if (updateCustomer.getPaymentamt() != 0.00) currentCustomer.setPaymentamt(updateCustomer.getPaymentamt());
        if (updateCustomer.getOutstandingamt() != 0.00)
            currentCustomer.setOutstandingamt(updateCustomer.getOutstandingamt());
        if (updateCustomer.getPhone() != null) currentCustomer.setPhone(updateCustomer.getPhone());
        if (updateCustomer.getAgent() != null) currentCustomer.setAgent(updateCustomer.getAgent());

        if (updateCustomer.getOrders().size() > 0) {
            currentCustomer.getOrders().clear();
            for (Order o : updateCustomer.getOrders()) {
                Order newOrder = new Order();
                newOrder.setAdvanceamount(o.getAdvanceamount());
                newOrder.setOrdamount(o.getOrdamount());
                newOrder.setOrderdescription(o.getOrderdescription());
                newOrder.setOrdnum(o.getOrdnum());
                newOrder.setPayments(o.getPayments());
                newOrder.setCustomer(currentCustomer);

                currentCustomer.getOrders().add(newOrder);
            }

        }
        return customersRepository.save(currentCustomer);

    }

    @Transactional
    @Override
    public void delete(long id) {
        if(customersRepository.findById(id).isPresent()){
            customersRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Customer "+id+" not found");
        }
    }

}
