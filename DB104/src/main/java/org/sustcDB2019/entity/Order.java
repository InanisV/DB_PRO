package org.sustcDB2019.entity;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private Integer orderId;

    private Date arrivalTime;

    private Date departureTime;

    private Integer deliveryUserId;

    private Integer customerUserId;

    public ArrayList<Sales> getSaleses() { return saleses; }

    public void setSaleses(ArrayList<Sales> saleses) {
        this.saleses = saleses;
    }

    private ArrayList<Sales> saleses;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getDeliveryUserId() {
        return deliveryUserId;
    }

    public void setDeliveryUserId(Integer deliveryUserId) {
        this.deliveryUserId = deliveryUserId;
    }

    public Integer getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(Integer customerUserId) {
        this.customerUserId = customerUserId;
    }
}