package org.sustcDB2019.entity;

import java.math.BigDecimal;

public class Customer extends User {
    private Integer userId;

    private String address;

    private BigDecimal customerLong;

    private BigDecimal customerLati;

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    private int warehouseId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getCustomerLong() {
        return customerLong;
    }

    public void setCustomerLong(BigDecimal customerLong) {
        this.customerLong = customerLong;
    }

    public BigDecimal getCustomerLati() {
        return customerLati;
    }

    public void setCustomerLati(BigDecimal customerLati) {
        this.customerLati = customerLati;
    }
}