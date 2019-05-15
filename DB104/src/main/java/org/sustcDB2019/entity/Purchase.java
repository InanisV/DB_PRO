package org.sustcDB2019.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Purchase {
    private Integer purchaseId;

    private Integer goodsGoodsId;

    private Date date;

    private Integer amount;

    private BigDecimal cost;

    private Date productionDate;

    private Integer warehouseWarehouseId;

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getGoodsGoodsId() {
        return goodsGoodsId;
    }

    public void setGoodsGoodsId(Integer goodsGoodsId) {
        this.goodsGoodsId = goodsGoodsId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Integer getWarehouseWarehouseId() {
        return warehouseWarehouseId;
    }

    public void setWarehouseWarehouseId(Integer warehouseWarehouseId) {
        this.warehouseWarehouseId = warehouseWarehouseId;
    }
}