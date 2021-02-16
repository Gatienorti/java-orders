package com.lambda.com.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long custcode;
    @Column(nullable = false, unique = true)

    private String custname;
    private String workingarea;
    private String custcity;
    private String custcountry;
    private String grade;
    private double openingamt;
    private double receiveamt;
    private double paymentamt;
    private double outstandingamt;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "agentcode", nullable = false)
    @JsonIgnoreProperties(value="customers", allowSetters = true)
    private Agent agent;

    public Customer() {
    }

    public Customer(String custname, String custcity, String workingarea, String custcountry, String grade, double openingamt, double receiveamt, double paymentamt, double outstandingamt, String phone, Agent agent) {
        this.custname = custname;
        this.custcity = custcity;
        this.workingarea = workingarea;
        this.custcountry = custcountry;
        this.grade = grade;
        this.openingamt = openingamt;
        this.receiveamt = receiveamt;
        this.paymentamt = paymentamt;
        this.outstandingamt = outstandingamt;
        this.phone = phone;
        this.agent = agent;
    }

    public String getCustcity() {
        return custcity;
    }

    public void setCustcity(String custCity) {
        this.custcity = custCity;
    }

    public Long getCustcode() {
        return custcode;
    }

    public void setCustcode(Long custCode) {
        this.custcode = custCode;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custName) {
        this.custname = custName;
    }

    public String getWorkingarea() {
        return workingarea;
    }

    public void setWorkingarea(String workingArea) {
        this.workingarea = workingArea;
    }

    public String getCustcountry() {
        return custcountry;
    }

    public void setCustcountry(String custCountry) {
        this.custcountry = custCountry;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getOpeningamt() {
        return openingamt;
    }

    public void setOpeningamt(double openingAmt) {
        this.openingamt = openingAmt;
    }

    public double getReceiveamt() {
        return receiveamt;
    }

    public void setReceiveamt(double receiveAmt) {
        this.receiveamt = receiveAmt;
    }

    public double getPaymentamt() {
        return paymentamt;
    }

    public void setPaymentamt(double paymentAmt) {
        this.paymentamt = paymentAmt;
    }

    public double getOutstandingamt() {
        return outstandingamt;
    }

    public void setOutstandingamt(double outstandingAmt) {
        this.outstandingamt = outstandingAmt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
