package org.sustcDB2019.entity;

import javax.persistence.criteria.CriteriaBuilder;

public class GoodsWithAmount extends Goods {
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    private Integer amount;

}
