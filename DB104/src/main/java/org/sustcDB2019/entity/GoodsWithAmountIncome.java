package org.sustcDB2019.entity;

public class GoodsWithAmountIncome extends Goods {
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    private Integer amount;

    private Integer income;

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }
}
