package org.sustcDB2019.entity;

import java.math.BigDecimal;

public class Goods {
    private Integer goodsId;

    private String name;

    private String brand;

    private String originPlace;

    private Integer preserveTime;

    private Integer volume;

    private String refrigiratedCondition;

    private BigDecimal price;

    private BigDecimal discount;

    private String catagory;

    private String type;


    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public Integer getPreserveTime() {
        return preserveTime;
    }

    public void setPreserveTime(Integer preserveTime) {
        this.preserveTime = preserveTime;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getRefrigiratedCondition() {
        return refrigiratedCondition;
    }

    public void setRefrigiratedCondition(String refrigiratedCondition) {
        this.refrigiratedCondition = refrigiratedCondition;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}