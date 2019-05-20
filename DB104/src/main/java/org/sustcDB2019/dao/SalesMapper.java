package org.sustcDB2019.dao;

import org.sustcDB2019.entity.GoodsWithAmountIncome;
import org.sustcDB2019.entity.Sales;

import java.util.ArrayList;
import java.util.Date;

public interface SalesMapper {
    //warehouseId 可以为空
    ArrayList<GoodsWithAmountIncome> getSalesIncomeRank(Integer warehouseId, int itemsPerPage, int PageIndex);

    Integer countPaymentByIdAndDate(Integer customerUserId, Date startDate, Date endDate);

    ArrayList<GoodsWithAmountIncome> getSalesVolumeRank(Integer warehouseId, int itemsPerPage, int PageIndex);

    //need to be tested
    ArrayList<Sales> selectByCase(Sales sales);


    int deleteByPrimaryKey(Integer salesId);

    int insert(Sales record);

    int insertSelective(Sales record);

    Sales selectByPrimaryKey(Integer salesId);

    int updateByPrimaryKeySelective(Sales record);

    int updateByPrimaryKey(Sales record);
}