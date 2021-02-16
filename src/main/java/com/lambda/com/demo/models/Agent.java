package com.lambda.com.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long agentCode;
    @Column(nullable = false, unique = true)

    private String agentName;
    private String WorkingArea;
    private double commission;
    private String phone;
    private String country;


    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value="agent", allowSetters = true)
    private List<Customer> customer = new ArrayList<>();
    public Agent() {
    }

    public Agent(String agentName, String workingArea, double commission, String phone, String country) {
        this.agentName = agentName;
        WorkingArea = workingArea;
        this.commission = commission;
        this.phone = phone;
        this.country = country;
    }

    public long getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(long agentCode) {
        this.agentCode = agentCode;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getWorkingArea() {
        return WorkingArea;
    }

    public void setWorkingArea(String workingArea) {
        WorkingArea = workingArea;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }
}
