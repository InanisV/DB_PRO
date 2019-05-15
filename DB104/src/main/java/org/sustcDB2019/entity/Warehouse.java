package org.sustcDB2019.entity;

import java.math.BigDecimal;

public class Warehouse {
    private Integer warehouseId;

    private String address;

    private Integer refrigeratedShelfVolume;

    private Integer ordinaryShelfVolume;

    private BigDecimal warehouseLong;

    private BigDecimal warehouseLati;

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRefrigeratedShelfVolume() {
        return refrigeratedShelfVolume;
    }

    public void setRefrigeratedShelfVolume(Integer refrigeratedShelfVolume) {
        this.refrigeratedShelfVolume = refrigeratedShelfVolume;
    }

    public Integer getOrdinaryShelfVolume() {
        return ordinaryShelfVolume;
    }

    public void setOrdinaryShelfVolume(Integer ordinaryShelfVolume) {
        this.ordinaryShelfVolume = ordinaryShelfVolume;
    }

    public BigDecimal getWarehouseLong() {
        return warehouseLong;
    }

    public void setWarehouseLong(BigDecimal warehouseLong) {
        this.warehouseLong = warehouseLong;
    }

    public BigDecimal getWarehouseLati() {
        return warehouseLati;
    }

    public void setWarehouseLati(BigDecimal warehouseLati) {
        this.warehouseLati = warehouseLati;
    }
}