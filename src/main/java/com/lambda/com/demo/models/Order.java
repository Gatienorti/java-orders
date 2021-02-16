package com.lambda.com.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long ordNum;

    private double OrdAmount;
    private double AdvanceAmount;
    private String orderDescription;

    @ManyToMany
    @JoinTable(name ="orderspayments", joinColumns = @JoinColumn(name = "ordNum"), inverseJoinColumns = @JoinColumn(name = "paymentId"))
    @JsonIgnoreProperties(value="orders", allowSetters = true)
    private Set<Payment> payments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "custCode", nullable = false)
    @JsonIgnoreProperties(value="orders", allowSetters = true)
    private Customer customer;

    public Order() {
    }

    public Order(double ordAmount, double advanceAmount, Customer customer,String orderDescription) {
        OrdAmount = ordAmount;
        AdvanceAmount = advanceAmount;
        this.orderDescription = orderDescription;
        this.customer = customer;
    }

    public long getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(long ordNum) {
        this.ordNum = ordNum;
    }

    public double getOrdAmount() {
        return OrdAmount;
    }

    public void setOrdAmount(double ordAmount) {
        OrdAmount = ordAmount;
    }

    public double getAdvanceAmount() {
        return AdvanceAmount;
    }

    public void setAdvanceAmount(double advanceAmount) {
        AdvanceAmount = advanceAmount;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
