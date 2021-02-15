package com.lambda.com.demo.models;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long paymentId;

    @Column(nullable = false,unique = true)
    private String type;

    @ManyToMany(mappedBy = "payments")
    private Set<Order> orders = new HashSet<>();

    public Payment() {
    }

    public Payment(String type) {
        this.type = type;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
