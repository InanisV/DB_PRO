package org.sustcDB2019.entity;

public class Cashier extends User{
    private Integer userId;

    private Integer warehouseWarehouseId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getWarehouseWarehouseId() {
        return warehouseWarehouseId;
    }

    public void setWarehouseWarehouseId(Integer warehouseWarehouseId) {
        this.warehouseWarehouseId = warehouseWarehouseId;
    }
}