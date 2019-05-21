package org.sustcDB2019.entity;

public class Manager extends User {
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

    public void setByUser(User user1){
        this.setUserName(user1.getUserName());
        this.setId(user1.getId());
        this.setPhoneNumber(user1.getPhoneNumber());
        this.setPassword(user1.getPassword());
    }

    public Manager(User user1){
        this.setUserName(user1.getUserName());
        this.setId(user1.getId());
        this.setPhoneNumber(user1.getPhoneNumber());
        this.setPassword(user1.getPassword());
    }

    public Manager(){}
}