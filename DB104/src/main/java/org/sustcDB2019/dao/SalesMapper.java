package org.sustcDB2019.dao;

import org.sustcDB2019.entity.GoodsWithAmount;
import org.sustcDB2019.entity.Sales;

import java.util.ArrayList;

public interface SalesMapper {
    ArrayList<GoodsWithAmount> getSalesVolumeRank(Integer warehouseId, int itemsPerPage, int PageIndex);

    //need to be tested
    ArrayList<Sales> selectByCase(Sales sales);


    int deleteByPrimaryKey(Integer salesId);

    int insert(Sales record);

    int insertSelective(Sales record);

    Sales selectByPrimaryKey(Integer salesId);

    int updateByPrimaryKeySelective(Sales record);

    int updateByPrimaryKey(Sales record);
}