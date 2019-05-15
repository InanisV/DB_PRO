package org.sustcDB2019.entity;

public class Deliverer extends User {
    private Integer userId;

    private String statusOn;

    private Integer warehouseWarehouseId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatusOn() {
        return statusOn;
    }

    public void setStatusOn(String statusOn) {
        this.statusOn = statusOn;
    }

    public Integer getWarehouseWarehouseId() {
        return warehouseWarehouseId;
    }

    public void setWarehouseWarehouseId(Integer warehouseWarehouseId) {
        this.warehouseWarehouseId = warehouseWarehouseId;
    }
}