package org.sustcDB2019.entity;

import java.util.Date;

public class GoodsInWarehouse {
    private Integer idgoodsInWarehouse;

    private Integer warehouseWarehouseId;

    private Integer goodsGoodsId;

    private Integer amount;

    private Date expiredDay;

    public Integer getIdgoodsInWarehouse() {
        return idgoodsInWarehouse;
    }

    public void setIdgoodsInWarehouse(Integer idgoodsInWarehouse) {
        this.idgoodsInWarehouse = idgoodsInWarehouse;
    }

    public Integer getWarehouseWarehouseId() {
        return warehouseWarehouseId;
    }

    public void setWarehouseWarehouseId(Integer warehouseWarehouseId) {
        this.warehouseWarehouseId = warehouseWarehouseId;
    }

    public Integer getGoodsGoodsId() {
        return goodsGoodsId;
    }

    public void setGoodsGoodsId(Integer goodsGoodsId) {
        this.goodsGoodsId = goodsGoodsId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getExpiredDay() {
        return expiredDay;
    }

    public void setExpiredDay(Date expiredDay) {
        this.expiredDay = expiredDay;
    }
}