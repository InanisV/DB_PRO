package org.sustcDB2019.dao;

import org.sustcDB2019.entity.Sales;

import java.util.ArrayList;

public interface SalesMapper {
    //need to be tested
    ArrayList<Sales> selectByCase(Sales sales);


    int deleteByPrimaryKey(Integer salesId);

    int insert(Sales record);

    int insertSelective(Sales record);

    Sales selectByPrimaryKey(Integer salesId);

    int updateByPrimaryKeySelective(Sales record);

    int updateByPrimaryKey(Sales record);
}