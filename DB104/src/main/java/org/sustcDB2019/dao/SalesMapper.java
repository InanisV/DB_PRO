package org.sustcDB2019.dao;

import org.sustcDB2019.entity.Sales;

import java.util.ArrayList;

public interface SalesMapper {
    int deleteByCase(Sales sales);

    int deleteByPrimaryKey(Integer salesId);

    int insert(Sales record);

    int insertSelective(Sales record);

    ArrayList<Sales> selectByCase(Sales sales);

    Sales selectByPrimaryKey(Integer salesId);

    int updateByPrimaryKeySelective(Sales record);

    int updateByPrimaryKey(Sales record);
}