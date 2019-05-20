package org.sustcDB2019.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Sales {
    private Integer salesId;

    private Integer customerUserId;

    private Integer amount;

    private BigDecimal payment;

    private String isPaid;

    private Integer orderOrderId;

    private Integer goodsInWarehouseId;

    private Date salesTime;

    private GoodsInWarehouse goodsInWarehouse;

    public GoodsInWarehouse getGoodsInWarehouse() { return goodsInWarehouse; }

    public void setGoodsInWarehouse(GoodsInWarehouse goodsInWarehouse) { this.goodsInWarehouse = goodsInWarehouse; }

    public Integer getGoodsInWarehouseId() {
        return goodsInWarehouseId;
    }

    public void setGoodsInWarehouseId(Integer goodsInWarehouseId) {
        this.goodsInWarehouseId = goodsInWarehouseId;
    }

    public Date getSalesTime() { return salesTime; }

    public void setSalesTime(Date salesTime) { this.salesTime = salesTime; }

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public Integer getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(Integer customerUserId) {
        this.customerUserId = customerUserId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public Integer getOrderOrderId() {
        return orderOrderId;
    }

    public void setOrderOrderId(Integer orderOrderId) {
        this.orderOrderId = orderOrderId;
    }
}