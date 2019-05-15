package org.sustcDB2019.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsInWarehouse {
    private Integer idgoodsInWarehouse;

    private Integer totalVolume;

    private Date expiredDay;

    private Integer warehouseWarehouseId;

    private Integer goodsGoodsId;

    private BigDecimal amount;

    public Integer getIdgoodsInWarehouse() {
        return idgoodsInWarehouse;
    }

    public void setIdgoodsInWarehouse(Integer idgoodsInWarehouse) {
        this.idgoodsInWarehouse = idgoodsInWarehouse;
    }

    public Integer getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Integer totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Date getExpiredDay() {
        return expiredDay;
    }

    public void setExpiredDay(Date expiredDay) {
        this.expiredDay = expiredDay;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}